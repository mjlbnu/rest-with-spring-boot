package br.com.programadorbr.services;

import br.com.programadorbr.data.vo.v1.BookVO;
import br.com.programadorbr.exceptions.RequiredObjectIsNullException;
import br.com.programadorbr.mapper.mocks.MockBook;
import br.com.programadorbr.model.Book;
import br.com.programadorbr.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {

    MockBook mock;

    @InjectMocks
    private BookServices bookServices;

    @Mock
    BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        mock = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllBooksTest() {
        List<Book> list = mock.mockBookList();
        when(bookRepository.findAll()).thenReturn(list);
        var bookList = bookServices.findAll();

        assertNotNull(bookList);
        assertEquals(14, bookList.size());
    }

    @Test
    void findBookByIdTest() {
        Book book = mock.mockBook(1);

        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        var result = bookServices.findById(book.getId());
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Title 1", result.getTitle());
        assertEquals(45D, result.getPrice());
        assertNotNull(result.getLaunchDate());
        assertEquals("Author 1", result.getAuthor());
    }

    @Test
    void createBookTest() {
        Book entity = mock.mockBook(1);
        entity.setId(1L);

        Book persisted = entity;
        persisted.setId(1L);

        BookVO vo = mock.mockBookVO(1);
        vo.setKey(1L);

        when(bookRepository.save(any())).thenReturn(persisted);

        var result = bookServices.create(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Author 1", result.getAuthor());
        assertEquals("Title 1", result.getTitle());
        assertEquals(45D, result.getPrice());
        assertNotNull(result.getLaunchDate());
    }

    @Test
    void update() {
        Book book = mock.mockBook(1);
        BookVO bookVO = mock.mockBookVO(1);
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);

        var result = bookServices.update(bookVO);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Author 1", result.getAuthor());
        assertEquals("Title 1", result.getTitle());
        assertEquals(45D, result.getPrice());
        assertNotNull(result.getLaunchDate());
    }

    @Test
    void delete() {
        Book entity = mock.mockBook(1);
        when(bookRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        bookServices.delete(1L);
    }

    @Test
    void createWithNullTest() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            bookServices.create(null);
        });
        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void updateWithNullTest() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            bookServices.update(null);
        });
        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}