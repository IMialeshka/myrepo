package it.mialeshka.mapper;

import it.mialeshka.dto.BookDto;
import it.mialeshka.entity.Book;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-11-30T12:39:52-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_31 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDto toBookDto(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDto bookDto = new BookDto();

        bookDto.setFileCover( book.getFileCover() );
        bookDto.setIdBook( book.getIdBook() );
        bookDto.setName( book.getName() );
        bookDto.setWriter( book.getWriter() );
        bookDto.setGenre( book.getGenre() );

        return bookDto;
    }

    @Override
    public Book toBook(BookDto bookDto) {
        if ( bookDto == null ) {
            return null;
        }

        Book book = new Book();

        book.setFileCover( bookDto.getFileCover() );
        book.setIdBook( bookDto.getIdBook() );
        book.setName( bookDto.getName() );
        book.setWriter( bookDto.getWriter() );
        book.setGenre( bookDto.getGenre() );

        return book;
    }
}
