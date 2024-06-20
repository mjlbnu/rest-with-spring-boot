package br.com.programadorbr.mapper.mocks;

import br.com.programadorbr.data.vo.v1.BookVO;
import br.com.programadorbr.model.Book;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockBook {

    public Book mockBook(Integer number) {
        Book book = new Book();
        book.setId(number.longValue());
        book.setAuthor("Author " + number);
        book.setLaunchDate(new Date());
        book.setPrice(45D);
        book.setTitle("Title " + number);
        return book;
    }

    public BookVO mockBookVO(Integer number) {
        BookVO bookVO = new BookVO();
        bookVO.setKey(number.longValue());
        bookVO.setAuthor("Author " + number);
        bookVO.setLaunchDate(new Date());
        bookVO.setPrice(45D);
        bookVO.setTitle("Title " + number);
        return bookVO;
    }

    public List<Book> mockBookList() {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockBook(i));
        }
        return books;
    }

    public List<BookVO> mockBookVOList() {
        List<BookVO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockBookVO(i));
        }
        return books;
    }
}
