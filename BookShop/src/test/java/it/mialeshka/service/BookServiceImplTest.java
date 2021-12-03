package it.mialeshka.service;

import it.mialeshka.dto.BookDto;
import it.mialeshka.service.impl.BookServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.hamcrest.core.Is.is;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@Sql(scripts = "classpath:init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class BookServiceImplTest{
    @Autowired
    BookServiceImpl bookService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test1() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "book");
        BookDto bookDto = new BookDto();
        bookDto.setName("test1");
        bookDto.setWriter("test1");
        bookDto.setGenre("test1");
        bookService.addBook(bookDto);
        Assert.assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "book"), is(1));

    }

    @Test
    public void test2() {
        Assert.assertThat(bookService.allBooks().size(), is(2));
    }

}
