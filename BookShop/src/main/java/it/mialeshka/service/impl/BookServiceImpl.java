package it.mialeshka.service.impl;

import it.mialeshka.dto.BookDto;
import it.mialeshka.entity.Book;
import it.mialeshka.mapper.BookMapper;
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
    private BookRepository bookRepository;
    private BookMapper bookMapper;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Autowired
    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDto addBook(BookDto bookDto) {
        return bookMapper.toBookDto(bookRepository.saveAndFlush(bookMapper.toBook(bookDto)));
    }

    @Value("${file.upload-dir}")
    private String libraryDir;

    @Value("${file.cover-dir}")
    private String coverDir;

    @Override
    public String getLibraryDir() {
        return libraryDir;
    }

    @Override
    public String getCoverDir() {
        return coverDir;
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
        String nameBook = UUID.randomUUID().toString() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String pathToBook = libraryDir.replace("/", File.separator) + File.separator + nameBook;
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
        Map<String, String> metaInfo = new HashMap<>();
        String extensionFile = nameBookFile.substring(nameBookFile.lastIndexOf(".")+1);
        String fileCover = UUID.randomUUID().toString() + ".png";
        String pathToBook = libraryDir.replace("/", File.separator) + File.separator + nameBookFile;
        String pathToCover = coverDir.replace("/", File.separator);
        metaInfo.put("bookFile", nameBookFile);

        if(extensionFile.equalsIgnoreCase(FileFormats.EPUB.name())){
            EpubReader epubReader = new EpubReader();
            nl.siegmann.epublib.domain.Book bookEpub = null;
            Resource coverImage = null;
            try {
                bookEpub = epubReader.readEpub(new FileInputStream(new File(pathToBook)));
                coverImage = bookEpub.getCoverImage();
                metaInfo.put("writer", bookEpub.getMetadata().getAuthors().get(0).toString());
                metaInfo.put("name", bookEpub.getMetadata().getTitles().get(0));
            } catch (IOException e) {
                throw new LoadBookException("Problem with getting metainfo: "  + e.getMessage());
            }


            if(coverImage != null){
                  try {
                    BufferedImage bImage = Imaging.getBufferedImage(coverImage.getData());
                    ImageIO.write(bImage, "png", new File(pathToCover + File.separator +fileCover));
                    metaInfo.put("bookCover", fileCover);
                } catch (ImageReadException e) {
                      throw new LoadBookException("Problem with getting metainfo: "  + e.getMessage());
                } catch (IOException e) {
                      throw new LoadBookException("Problem with getting metainfo: "  + e.getMessage());
                }
            }
            else{
                try {
                    BufferedImage bImage = Imaging.getBufferedImage(new File(pathToCover + File.separator + "DefaultCovers.jpg"));
                    ImageIO.write(bImage, "png", new File(pathToCover + File.separator +fileCover));
                    metaInfo.put("bookCover", fileCover);
                } catch (ImageReadException e) {
                    throw new LoadBookException("Problem with getting metainfo: "  + e.getMessage());
                } catch (IOException e) {
                    throw new LoadBookException("Problem with getting metainfo: "  + e.getMessage());
                }
            }
        }

        if(extensionFile.equalsIgnoreCase(FileFormats.PDF.name())){
             try(PDDocument doc = PDDocument.load( new File(pathToBook))) {
                 metaInfo.put("writer",  doc.getDocumentInformation().getAuthor());
                 metaInfo.put("name",  doc.getDocumentInformation().getTitle());
                 PDFRenderer pdfRenderer = new PDFRenderer(doc);
                 BufferedImage bImage = pdfRenderer.renderImageWithDPI(0, 300, ImageType.RGB);
                 ImageIO.write(bImage, "png", new File(pathToCover + File.separator + fileCover));
                 metaInfo.put("bookCover", fileCover);
            } catch (IOException e) {
                 throw new LoadBookException("Problem with getting metainfo: "  + e.getMessage());
            }

        }
        return metaInfo;
    }

}
