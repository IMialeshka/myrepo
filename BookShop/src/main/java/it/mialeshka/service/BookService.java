package it.mialeshka.service;

import it.mialeshka.dto.BookDto;
import it.mialeshka.util.exception.LoadBookException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface BookService {
  BookDto addBook(BookDto book);

  String getPathToLibrary();

  String getPathToCovers();

  List<BookDto> allBooks();
  List<BookDto> allUserBooks(Long userId);
  String copyFileBook(MultipartFile file) throws LoadBookException;
  Map<String, String> getMetaInfoBook(String nameBookFile) throws LoadBookException;
  void addBookSolr(BookDto bookDto);
}
