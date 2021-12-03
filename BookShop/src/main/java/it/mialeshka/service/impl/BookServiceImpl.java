package it.mialeshka.service.impl;

import it.mialeshka.dto.BookDto;
import it.mialeshka.entity.Book;
import it.mialeshka.mapper.BookMapper;
import it.mialeshka.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.mialeshka.repository.BookRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    public List<BookDto> allWriterBooks(String writerName) {

        List<Book> bookList =  bookRepository.indWriterBook(writerName);
        List<BookDto> bookDtoList = new ArrayList<>();
        for (Book element: bookList
        ) {
            bookDtoList.add(bookMapper.toBookDto(element));
        }
        return bookDtoList;
    }
}
