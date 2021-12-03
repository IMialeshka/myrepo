package it.mialeshka.mapper;

import it.mialeshka.dto.BookFileDto;
import it.mialeshka.entity.BookFile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookFileMapper {
    BookFileDto toBookFileDto(BookFile bookFile);
    BookFile toBookFile(BookFileDto bookFileDto);
}
