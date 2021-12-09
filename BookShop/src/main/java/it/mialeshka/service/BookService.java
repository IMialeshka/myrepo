package it.mialeshka.service;

import it.mialeshka.dto.BookDto;
import it.mialeshka.util.exception.LoadBookException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface BookService {
  public BookDto addBook(BookDto book);

  String getLibraryDir();

  String getCoverDir();

  public List<BookDto> allBooks();
  public List<BookDto> allUserBooks(Long userId);
  public String copyFileBook(MultipartFile file) throws LoadBookException;
  public Map<String, String> getMetaInfoBook(String nameBookFile) throws LoadBookException;
}
