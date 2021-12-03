package it.mialeshka.mapper;

import it.mialeshka.dto.BookDto;
import it.mialeshka.dto.BookFileDto;
import it.mialeshka.entity.Book;
import it.mialeshka.entity.BookFile;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-11-30T12:39:52-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_31 (Oracle Corporation)"
)
@Component
public class BookFileMapperImpl implements BookFileMapper {

    @Override
    public BookFileDto toBookFileDto(BookFile bookFile) {
        if ( bookFile == null ) {
            return null;
        }

        BookFileDto bookFileDto = new BookFileDto();

        bookFileDto.setIdBookFile( bookFile.getIdBookFile() );
        bookFileDto.setNameFile( bookFile.getNameFile() );
        bookFileDto.setExtensionFile( bookFile.getExtensionFile() );
        bookFileDto.setBook( bookToBookDto( bookFile.getBook() ) );

        return bookFileDto;
    }

    @Override
    public BookFile toBookFile(BookFileDto bookFileDto) {
        if ( bookFileDto == null ) {
            return null;
        }

        BookFile bookFile = new BookFile();

        bookFile.setBook( bookDtoToBook( bookFileDto.getBook() ) );
        bookFile.setIdBookFile( bookFileDto.getIdBookFile() );
        bookFile.setNameFile( bookFileDto.getNameFile() );
        bookFile.setExtensionFile( bookFileDto.getExtensionFile() );

        return bookFile;
    }

    protected BookDto bookToBookDto(Book book) {
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

    protected Book bookDtoToBook(BookDto bookDto) {
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
