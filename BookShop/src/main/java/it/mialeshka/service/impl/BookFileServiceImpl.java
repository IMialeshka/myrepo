package it.mialeshka.service.impl;

import it.mialeshka.dto.BookFileDto;
import it.mialeshka.mapper.BookFileMapper;
import it.mialeshka.mapper.BookMapper;
import it.mialeshka.repository.BookFileRepository;
import it.mialeshka.service.BookFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookFileServiceImpl implements BookFileService {
    @Autowired
    BookFileRepository bookFileRepository;

    @Autowired
    BookFileMapper bookFileMapper;

    @Override
    public List<BookFileDto> allBookFile(String bookName) {
        return null;
    }

    @Override
    public List<BookFileDto> allBookFile(String bookName, String extensionFile) {
        return null;
    }

    @Override
    public void addBookFile(BookFileDto bookFile) {
        bookFileRepository.saveAndFlush(bookFileMapper.toBookFile(bookFile));
    }
}
