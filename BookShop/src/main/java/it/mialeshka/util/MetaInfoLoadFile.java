package it.mialeshka.util;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubReader;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MetaInfoLoadFile {

    public static Map<String, String> getMetaInfo(String pathBook, String pathJpg, String file) throws IOException, ImageReadException {
        Map<String, String> metaInfo = new HashMap<>();
        String extensionFile = file.substring(file.lastIndexOf(".")+1);
        String fileTitle = UUID.randomUUID().toString() + ".png";
        if(extensionFile.equalsIgnoreCase(FileFormats.EPUB.name())){
            EpubReader epubReader = new EpubReader();
            Book bookEpub = epubReader.readEpub(new FileInputStream(new File(pathBook + File.separator + file)));
            metaInfo.put("writer", bookEpub.getMetadata().getAuthors().get(0).toString());
            metaInfo.put("name", bookEpub.getMetadata().getTitles().get(0));

            Resource coverImage = bookEpub.getCoverImage();
            if(coverImage != null){
                BufferedImage bImage = Imaging.getBufferedImage(coverImage.getData());
                ImageIO.write(bImage, "png", new File(pathJpg + File.separator +fileTitle));
                metaInfo.put("file_cover", fileTitle);
            }
            else{
                BufferedImage bImage = Imaging.getBufferedImage(new File (pathJpg + File.separator + "DefaultCovers.jpg"));
                ImageIO.write(bImage, "png", new File(pathJpg + File.separator +fileTitle));
                metaInfo.put("file_cover", fileTitle);
            }
        }

        if(extensionFile.equalsIgnoreCase(FileFormats.PDF.name())){
            PDDocument doc = PDDocument.load( new File(pathBook + File.separator + file));
            metaInfo.put("writer",  doc.getDocumentInformation().getAuthor());
            metaInfo.put("name",  doc.getDocumentInformation().getTitle());
            PDFRenderer pdfRenderer = new PDFRenderer(doc);
            BufferedImage bImage = pdfRenderer.renderImageWithDPI(0, 300, ImageType.RGB);
            ImageIO.write(bImage, "png", new File(pathJpg + File.separator + fileTitle));
            metaInfo.put("path_file_cover", pathJpg + File.separator + fileTitle);
            metaInfo.put("file_cover", fileTitle);
            doc.close();
        }

        return metaInfo;
    }


}
