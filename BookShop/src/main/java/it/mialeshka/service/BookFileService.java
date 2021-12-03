package it.mialeshka.service;

import it.mialeshka.dto.BookDto;
import it.mialeshka.dto.BookFileDto;

import java.util.List;

public interface BookFileService {
    public List<BookFileDto> allBookFile(String bookName);
    public List<BookFileDto> allBookFile(String bookName, String extensionFile);
    public void addBookFile(BookFileDto bookFile);
}
