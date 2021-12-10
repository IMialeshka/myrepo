package it.mialeshka.rest;

import it.mialeshka.dto.BookDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.mialeshka.dto.UserShopDto;
import it.mialeshka.entity.UserShop;
import it.mialeshka.service.BookService;
import it.mialeshka.service.UserShopService;
import it.mialeshka.util.exception.LoadBookException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Api(value = "Books")
@RestController
public class BooksRestController {
   @Autowired
   private BookService bookService;

   @Autowired
   private UserShopService userShopService;


   @Autowired
   private ResourceLoader resourceLoader;


   @GetMapping(value = "/booksList")
   @ApiOperation("all books")
   public List<BookDto> readAllBooks(){
      return bookService.allBooks();
   }


   @PreAuthorize("hasRole('ROLE_ADMIN')")
   @PostMapping(value = "/getmetainfo", consumes = { "multipart/form-data" })
   public ResponseEntity<Map<String, String>> bookApprove(@RequestParam("file") MultipartFile file) throws LoadBookException {
      Map<String, String> metaInfoBook = new HashMap<>();
      if(!file.isEmpty() && (file.getContentType().equalsIgnoreCase("application/pdf") || file.getContentType().equalsIgnoreCase("application/epub+zip"))){
         String nameBookFile = bookService.copyFileBook(file);
         metaInfoBook.putAll(bookService.getMetaInfoBook(nameBookFile));
      }
      else{
         throw new LoadBookException("incorrect format file");
      }
      return ResponseEntity.ok(metaInfoBook);
   }

   @GetMapping(value = "/showCover/{coverFileName}")
   public ResponseEntity<Resource> showCover(@PathVariable String coverFileName){
      String path = bookService.getPathToCovers() + File.separator;
      try {
         return ResponseEntity.ok(resourceLoader.getResource("file:" + path + coverFileName));
      } catch (Exception e) {
         return ResponseEntity.notFound().build();
      }
   }

   @GetMapping(value = "/readBook/{bookFileName}")
   public ResponseEntity<Resource> readBook(@PathVariable String bookFileName){
      try {
         return ResponseEntity.ok(resourceLoader.getResource("file:" + bookService.getPathToLibrary() + File.separator + bookFileName));
      } catch (Exception e) {
         return ResponseEntity.notFound().build();
      }
   }


   @PreAuthorize("hasRole('ROLE_ADMIN')")
   @GetMapping("/justUserBooksList")
   @ApiOperation("all books user")
   public List<BookDto> readWriterBooks(){
      UserShop user= (UserShop) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      return bookService.allUserBooks(user.getId());
   }


   @PreAuthorize("hasRole('ROLE_ADMIN')")
   @PostMapping(value = "/saveNewBook")
   @ApiOperation("new book")
   public ResponseEntity<BookDto> addNewBook(@RequestParam("name") String name,
                            @RequestParam("writer") String writer,
                            @RequestParam("genre") String genre,
                            @RequestParam("bookFile") String bookFile,
                            @RequestParam("bookCover") String bookCover){
      BookDto bookDto = new BookDto();
      bookDto.setName(name);
      bookDto.setWriter(writer);
      bookDto.setGenre(genre);
      bookDto.setCoverBook(bookCover);
      bookDto.setFileName(bookFile);
      String nameUser = SecurityContextHolder.getContext().getAuthentication().getName();
      UserShopDto user = userShopService.findByUserName(nameUser);
      List<UserShopDto> userShopDto = new ArrayList<>();
      userShopDto.add(user);
      bookDto.setUserShopList(userShopDto);
      BookDto bookDtoResult = bookService.addBook(bookDto);
      bookService.addBookSolr(bookDtoResult);
      return ResponseEntity.ok(bookDtoResult);
   }


}
