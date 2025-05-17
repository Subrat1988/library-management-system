package org.lms.app.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lms.app.entity.Book;
import org.lms.app.exceptions.InvalidBookException;
import org.lms.app.repository.BookRepository;
import org.lms.app.request.BookRegistrationRequest;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    private static BookRepository bookRepository;

    private static BookService bookService;

    @BeforeEach
    void setUp() {
        bookRepository = Mockito.mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    void registerBook() {
        BookRegistrationRequest bookRegistrationRequest = new BookRegistrationRequest();
        bookRegistrationRequest.setIsbnCode("dummy isbn code");
        bookRegistrationRequest.setTitle("dummy title");
        bookRegistrationRequest.setAuthor("dummy author");

        Book book = Book.builder()
                .bookId(1)
                .isbnCode("dummy isbn code")
                .title("dummy title")
                .author("dummy author")
                .totalCopies(1)
                .availableCopies(1).build();

        Mockito.when(bookRepository.findByisbnCode(Mockito.any())).thenReturn(null);
        Mockito.when(bookRepository.save(Mockito.any())).thenReturn(book);

        Book bookObj = bookService.registerBook(bookRegistrationRequest);

        Mockito.verify(bookRepository, Mockito.times(1)).save(Mockito.any());

        Assertions.assertEquals(1, bookObj.getBookId());
        Assertions.assertEquals(1, bookObj.getTotalCopies());
        Assertions.assertEquals(1, bookObj.getAvailableCopies());
    }

    @Test
    void registerBook_2() {
        BookRegistrationRequest bookRegistrationRequest = new BookRegistrationRequest();
        bookRegistrationRequest.setIsbnCode("dummy isbn code");
        bookRegistrationRequest.setTitle("dummy title");
        bookRegistrationRequest.setAuthor("dummy author");

        Book book = Book.builder()
                .bookId(1)
                .isbnCode("dummy isbn code")
                .title("dummy different title")
                .author("dummy different author")
                .totalCopies(1)
                .availableCopies(1).build();

        Mockito.when(bookRepository.findByisbnCode(Mockito.any())).thenReturn(book);

        Assertions.assertThrows(InvalidBookException.class, () -> {
            bookService.registerBook(bookRegistrationRequest);
        });

    }

    @Test
    void registerBook_3() {
        BookRegistrationRequest bookRegistrationRequest = new BookRegistrationRequest();
        bookRegistrationRequest.setIsbnCode("dummy isbn code");
        bookRegistrationRequest.setTitle("dummy title");
        bookRegistrationRequest.setAuthor("dummy author");

        Book book = Book.builder()
                .bookId(1)
                .isbnCode("dummy isbn code")
                .title("dummy title")
                .author("dummy author")
                .totalCopies(2)
                .availableCopies(2).build();

        Mockito.when(bookRepository.findByisbnCode(Mockito.any())).thenReturn(book);
        Mockito.when(bookRepository.save(Mockito.any())).thenReturn(book);

        Book bookObj = bookService.registerBook(bookRegistrationRequest);

        Mockito.verify(bookRepository, Mockito.times(1)).save(Mockito.any());

        Assertions.assertEquals(1, bookObj.getBookId());
        Assertions.assertEquals(3, bookObj.getTotalCopies());
        Assertions.assertEquals(3, bookObj.getAvailableCopies());
    }

    @Test
    void findBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "dummy", "dummy", "dummy", 1, 1));
        books.add(new Book(2, "dummy", "dummy", "dummy", 2, 2));

        Mockito.when(bookRepository.findAll()).thenReturn(books);

        List<Book> bookList = bookService.findBooks();

        Assertions.assertEquals(books.size(), bookList.size());
    }
}
