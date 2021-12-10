package it.mialeshka.service.impl;

import it.mialeshka.dto.BookDto;
import it.mialeshka.entity.Book;
import it.mialeshka.entity.BookSolr;
import it.mialeshka.mapper.BookMapper;
import it.mialeshka.repository.BookSolrRepository;
import it.mialeshka.service.BookService;
import it.mialeshka.util.FileFormats;
import it.mialeshka.util.exception.LoadBookException;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubReader;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import it.mialeshka.repository.BookRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookSolrRepository bookSolrRepository;


    @Value("${file.upload-dir}")
    private String libraryDir;

    @Value("${file.cover-dir}")
    private String coverDir;


    @Override
    public void addBookSolr(BookDto bookDto) {
        BookSolr bookSolr = new BookSolr();
        bookSolr.setId(bookDto.getId().toString());
        bookSolr.setName(bookDto.getName());
        bookSolr.setWriter(bookDto.getWriter());
        bookSolr.setGenre(bookDto.getGenre());
        bookSolrRepository.save(bookSolr);
    }

    @Override
    public BookDto addBook(BookDto bookDto) {
        return bookMapper.toBookDto(bookRepository.saveAndFlush(bookMapper.toBook(bookDto)));
    }


    @Override
    public String getPathToLibrary() {
        return libraryDir.replace("/", File.separator);
    }

    @Override
    public String getPathToCovers() {
        return coverDir.replace("/", File.separator);
    }

    @Override
    public List<BookDto> allBooks() {
        List<Book> bookList = bookRepository.findAll();
        List<BookDto> bookDtoList = new ArrayList<>();
        for (Book element: bookList
             ) {
            bookDtoList.add(bookMapper.toBookDto(element));
        }
        return bookDtoList;
    }

    @Override
    public List<BookDto> allUserBooks(Long userId) {
        List<Book> bookList =  bookRepository.userBooks(userId);
        List<BookDto> bookDtoList = new ArrayList<>();
        for (Book element: bookList
        ) {
            bookDtoList.add(bookMapper.toBookDto(element));
        }
        return bookDtoList;
    }

    @Override
    public String copyFileBook(MultipartFile file) throws LoadBookException {
        String nameBook = UUID.randomUUID()+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String pathToBook = getPathToLibrary()+ File.separator + nameBook;
        try(BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(pathToBook)))) {
            byte[] bytes = file.getBytes();
            stream.write(bytes);
        } catch (FileNotFoundException e) {
            throw new LoadBookException("file dont found " + pathToBook);
        } catch (IOException e) {
            throw new LoadBookException("cannot make file:" + pathToBook);
        }
        return nameBook;
    }


    @Override
    public Map<String, String> getMetaInfoBook(String nameBookFile) throws LoadBookException {
        String extensionFile = nameBookFile.substring(nameBookFile.lastIndexOf(".")+1);
        Map<String, String> metaInfoBook = new HashMap<>();
        if(extensionFile.equalsIgnoreCase(FileFormats.EPUB.name())){
            metaInfoBook = getMetaInfoBookEpub(nameBookFile);
        }

        if(extensionFile.equalsIgnoreCase(FileFormats.PDF.name())){
            metaInfoBook = getMetaInfoBookPdf(nameBookFile);
        }
        return metaInfoBook;
    }

    private Map<String, String> getMetaInfoBookEpub(String fileBookName) throws LoadBookException {
        Map<String, String> metaInfo = new HashMap<>();
        String nameFileCover = UUID.randomUUID() + ".png";
        String pathToCovers = getPathToCovers();

        metaInfo.put("bookFile", fileBookName);
        EpubReader epubReader = new EpubReader();
        try {
            nl.siegmann.epublib.domain.Book bookEpub = epubReader.readEpub(new FileInputStream(new File(getPathToLibrary() + File.separator +  fileBookName)));
            Resource coverImage = bookEpub.getCoverImage();
            metaInfo.put("writer", bookEpub.getMetadata().getAuthors().get(0).toString());
            metaInfo.put("name", bookEpub.getMetadata().getTitles().get(0));
            if(coverImage != null){
                try {
                    BufferedImage bImage = Imaging.getBufferedImage(coverImage.getData());
                    ImageIO.write(bImage, "png", new File(pathToCovers + File.separator + nameFileCover));
                    metaInfo.put("bookCover", nameFileCover);
                } catch (ImageReadException | IOException e) {
                    throw new LoadBookException("Problem with getting metainfo: "  + e.getMessage());
                }
            }
            else{
                try {
                    BufferedImage bImage = Imaging.getBufferedImage(new File(pathToCovers + File.separator + "DefaultCovers.jpg"));
                    ImageIO.write(bImage, "png", new File(pathToCovers + File.separator +nameFileCover));
                    metaInfo.put("bookCover", nameFileCover);
                } catch (ImageReadException | IOException e) {
                    throw new LoadBookException("Problem with getting metainfo: "  + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new LoadBookException("Problem with getting metainfo: "  + e.getMessage());
        }


        return metaInfo;
    }

    private Map<String, String> getMetaInfoBookPdf(String fileBookName) throws LoadBookException {
        Map<String, String> metaInfo = new HashMap<>();
        String nameFileCover = UUID.randomUUID() + ".png";

        metaInfo.put("bookFile", fileBookName);
        try(PDDocument doc = PDDocument.load( new File(getPathToLibrary() + File.separator +  fileBookName))) {
            metaInfo.put("writer",  doc.getDocumentInformation().getAuthor());
            metaInfo.put("name",  doc.getDocumentInformation().getTitle());
            PDFRenderer pdfRenderer = new PDFRenderer(doc);
            BufferedImage bImage = pdfRenderer.renderImageWithDPI(0, 300, ImageType.RGB);
            ImageIO.write(bImage, "png", new File(getPathToCovers() + File.separator + nameFileCover));
            metaInfo.put("bookCover", nameFileCover);
        } catch (IOException e) {
            throw new LoadBookException("Problem with getting metainfo: "  + e.getMessage());
        }
        return metaInfo;
    }

}
