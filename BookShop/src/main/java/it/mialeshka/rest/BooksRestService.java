package it.mialeshka.rest;

import it.mialeshka.dto.BookDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.mialeshka.dto.BookFileDto;
import it.mialeshka.service.BookFileService;
import it.mialeshka.service.BookService;
import it.mialeshka.service.UserShopService;
import it.mialeshka.util.AuthResponse;
import it.mialeshka.util.MetaInfoLoadFile;
import it.mialeshka.util.exception.LoadBookException;
import org.apache.commons.imaging.ImageReadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Api(value = "Books")
@RestController
public class BooksRestService {
   @Autowired
   private BookService bookService;

   @Autowired
   private UserShopService userShopService;

   @Autowired
   BookFileService bookFileService;

   @Value("${file.upload-dir}")
   private String libraryDir;

   @Value("${file.jpg-dir}")
   private String jpgTitleDir;



   @GetMapping(value = "/booksList")
   @ApiOperation("all books")
   public List<BookDto> readAllBooks(){
      return bookService.allBooks();
   }


   @PostMapping(value = "/getmetainfo", consumes = { "multipart/form-data" })
   public ResponseEntity<?> bookApprove(@RequestParam("file") MultipartFile file, Model model) throws LoadBookException {
      String fullPath = "";
      Map<String, String> bookTitles = new HashMap<>();
      if(!file.isEmpty() && (file.getContentType().equalsIgnoreCase("application/pdf") || file.getContentType().equalsIgnoreCase("application/epub+zip"))){
         String nameBook = UUID.randomUUID().toString() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
         fullPath = libraryDir.replace("/", File.separator) + File.separator + nameBook;
         try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(fullPath)))){
            byte[] bytes = file.getBytes();
            stream.write(bytes);
            bookTitles.putAll(MetaInfoLoadFile.getMetaInfo(libraryDir.replace("/", File.separator) , jpgTitleDir.replace("/", File.separator), nameBook));
            bookTitles.put("bookFile", nameBook);
         } catch (FileNotFoundException e) {
           throw new LoadBookException("file dont found " + fullPath);
         } catch (IOException e) {
            throw new LoadBookException("cannot make file:" + fullPath);
         } catch (ImageReadException e) {
            throw new LoadBookException("Problem with getting metainfo: "  + e.getMessage());
         }
      }
      else{
         throw new LoadBookException("incorrect format file" + fullPath);
      }
      return ResponseEntity.ok(bookTitles);
   }


   @GetMapping("/{writer}")
   @ApiOperation("all books writer")
   public List<BookDto> readWriterBooks(@PathVariable  String writer){
      return bookService.allWriterBooks(writer);
   }


   @PostMapping(value = "/saveNewBook")
   @ApiOperation("new book")
   public String addNewBook(@RequestParam("name") String name,
                            @RequestParam("writer") String writer,
                            @RequestParam("genre") String genre,
                            @RequestParam("file_book_name") String file_book_name,
                            @RequestParam("file_cover") String file_cover){
      BookDto bookDto = new BookDto();
      bookDto.setName(name);
      bookDto.setWriter(writer);
      bookDto.setGenre(genre);
      bookDto.setFileCover(file_cover);
     /* String nameUser = SecurityContextHolder.getContext().getAuthentication().getName();
      UserShopDto user = userShopService.findByUserNameDto(nameUser);
      List<UserShopDto> userShopDto = new ArrayList<>();
      userShopDto.add(user);
      bookDto.setUserShopList(userShopDto);*/
      ///bookService.addBook(bookDto);
      BookFileDto bookFileDto = new BookFileDto();
      bookFileDto.setNameFile(file_book_name.substring(0, file_book_name.lastIndexOf(".")-1));
      bookFileDto.setExtensionFile(file_book_name.substring(file_book_name.lastIndexOf(".")+1));
      bookFileDto.setBook(bookDto);
      bookFileService.addBookFile(bookFileDto);
      return "redirect:booksViewAdmin";
   }


}
