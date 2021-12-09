package it.mialeshka.mapper;

import it.mialeshka.dto.BookDto;
import it.mialeshka.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDto toBookDto(Book book);
    Book toBook(BookDto bookDto);
}
