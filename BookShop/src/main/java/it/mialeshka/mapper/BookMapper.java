package it.mialeshka.mapper;

import it.mialeshka.dto.BookDto;
import it.mialeshka.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDto toBookDto(Book book);
    Book toBook(BookDto bookDto);
}
