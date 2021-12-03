package it.mialeshka.service;

import it.mialeshka.dto.BookDto;

import java.util.List;

public interface BookService {
  public BookDto addBook(BookDto book);
  public List<BookDto> allBooks();
  public List<BookDto> allWriterBooks(String writerName);
}
