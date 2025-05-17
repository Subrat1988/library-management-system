package org.lms.app.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lms.app.entity.Book;
import org.lms.app.request.BookRegistrationRequest;
import org.lms.app.response.Response;
import org.lms.app.service.BookService;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    private BookService bookService;
    private BookController bookController;

    @BeforeEach
    void setup() {
        bookService = Mockito.mock(BookService.class);
        bookController = new BookController(bookService);
    }

    @Test
    public void testRegister() {
        BookRegistrationRequest request = new BookRegistrationRequest();
        Book book = Book.builder().bookId(1).title("dummy title").author("dummy author").isbnCode("dummy isbn").build();

        Mockito.when(bookService.registerBook(Mockito.any(BookRegistrationRequest.class))).thenReturn(book);

        ResponseEntity responseEntity = bookController.register(request);

        Response response = (Response) responseEntity.getBody();

        Assertions.assertEquals("Success", response.getStatus());
        Assertions.assertEquals("Book registered successfully", response.getMessage());
    }

    @Test
    public void testFindBooks() {
        List<Book> books = new ArrayList();
        books.add(new Book(1, "dummy", "dummy", "dummy", 1, 1));
        books.add(new Book(2, "dummy", "dummy", "dummy", 2, 2));

        Mockito.when(bookService.findBooks()).thenReturn(books);

        ResponseEntity<Response<List<Book>>> responseEntity = bookController.getBooks();

        Response<List<Book>> response = responseEntity.getBody();

        Assertions.assertEquals("Success", response.getStatus());
        Assertions.assertEquals("Successfully fetched book records from Library Management System", response.getMessage());
        Assertions.assertEquals(2, ((List<Book>) response.getData()).size());
    }

    @Test
    public void testFindBooks_Fail() {
        Mockito.lenient().when(bookService.findBooks()).thenReturn(null);

        ResponseEntity responseEntity = bookController.getBooks();

        Response response = (Response) responseEntity.getBody();

        Assertions.assertEquals("Failed", response.getStatus());
        Assertions.assertEquals("No books found on Library Management System", response.getMessage());
        Assertions.assertNull(response.getData());
    }
}
