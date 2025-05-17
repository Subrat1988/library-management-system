package org.lms.app.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lms.app.constants.ApplicationConstants;
import org.lms.app.constants.BookIssueStatusConstants;
import org.lms.app.entity.Book;
import org.lms.app.entity.BookIssue;
import org.lms.app.entity.Borrower;
import org.lms.app.exceptions.BookIssueException;
import org.lms.app.repository.BookIssueRepository;
import org.lms.app.repository.BookRepository;
import org.lms.app.repository.BorrowerRepository;
import org.lms.app.request.BookIssueOrReturnRequest;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookIssueServiceTest {
    private BookIssueService bookIssueService;
    private BookRepository bookRepository;
    private BorrowerRepository borrowerRepository;
    private BookIssueRepository bookIssueRepository;

    @BeforeEach
    void setup() {
        bookRepository = Mockito.mock(BookRepository.class);
        borrowerRepository = Mockito.mock(BorrowerRepository.class);
        bookIssueRepository = Mockito.mock(BookIssueRepository.class);

        bookIssueService = new BookIssueService(bookIssueRepository, bookRepository, borrowerRepository);
    }

    @Test
    void issueBook() {
        BookIssueOrReturnRequest bookIssueOrReturnRequest = new BookIssueOrReturnRequest();
        bookIssueOrReturnRequest.setBookId(1);
        bookIssueOrReturnRequest.setBorrowerId(1);

        Book book = Book.builder()
                .bookId(1)
                .title("title")
                .author("author")
                .isbnCode("isbn")
                .totalCopies(1)
                .availableCopies(1).build();

        Borrower borrower = Borrower.builder()
                .borrowerId(1)
                .name("name")
                .email("email").build();

        BookIssue bookIssue = BookIssue.builder()
                .issueId(1)
                .book(book)
                .borrower(borrower)
                .issueDate(LocalDate.now())
                .issueStatus(BookIssueStatusConstants.STATUS_ISSUED).build();

        Mockito.when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        Mockito.when(borrowerRepository.findById(1)).thenReturn(Optional.of(borrower));
        Mockito.when(bookIssueRepository.save(Mockito.any())).thenReturn(bookIssue);
        Mockito.when(bookRepository.save(Mockito.any())).thenReturn(book);

        bookIssueService.issueBook(bookIssueOrReturnRequest);

        Mockito.verify(bookRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(bookIssueRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void issueBook_2() {
        BookIssueOrReturnRequest bookIssueOrReturnRequest = new BookIssueOrReturnRequest();
        bookIssueOrReturnRequest.setBookId(1);
        bookIssueOrReturnRequest.setBorrowerId(1);

        Borrower borrower = Borrower.builder()
                .borrowerId(1)
                .name("name")
                .email("email").build();

        Mockito.when(bookRepository.findById(1)).thenReturn(Optional.empty());
        Mockito.when(borrowerRepository.findById(1)).thenReturn(Optional.of(borrower));

        Assertions.assertThrows(BookIssueException.class, () -> {
            bookIssueService.issueBook(bookIssueOrReturnRequest);
        });
    }

    @Test
    void issueBook_3() {
        BookIssueOrReturnRequest bookIssueOrReturnRequest = new BookIssueOrReturnRequest();
        bookIssueOrReturnRequest.setBookId(1);
        bookIssueOrReturnRequest.setBorrowerId(1);

        Book book = Book.builder()
                .bookId(1)
                .title("title")
                .author("author")
                .isbnCode("isbn")
                .totalCopies(1)
                .availableCopies(1).build();

        Mockito.when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        Mockito.when(borrowerRepository.findById(1)).thenReturn(Optional.empty());

        Assertions.assertThrows(BookIssueException.class, () -> {
            bookIssueService.issueBook(bookIssueOrReturnRequest);
        });
    }

    @Test
    void issueBook_4() {
        BookIssueOrReturnRequest bookIssueOrReturnRequest = new BookIssueOrReturnRequest();
        bookIssueOrReturnRequest.setBookId(1);
        bookIssueOrReturnRequest.setBorrowerId(1);

        Book book = Book.builder()
                .bookId(1)
                .title("title")
                .author("author")
                .isbnCode("isbn")
                .totalCopies(1)
                .availableCopies(0).build();

        Borrower borrower = Borrower.builder()
                .borrowerId(1)
                .name("name")
                .email("email").build();

        Mockito.when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        Mockito.when(borrowerRepository.findById(1)).thenReturn(Optional.of(borrower));

        Assertions.assertThrows(BookIssueException.class, () -> {
            bookIssueService.issueBook(bookIssueOrReturnRequest);
        });
    }

    @Test
    void returnBook() {
        BookIssueOrReturnRequest bookIssueOrReturnRequest = new BookIssueOrReturnRequest();
        bookIssueOrReturnRequest.setBookId(1);
        bookIssueOrReturnRequest.setBorrowerId(1);

        Book book = Book.builder()
                .bookId(1)
                .title("title")
                .author("author")
                .isbnCode("isbn")
                .totalCopies(1)
                .availableCopies(1).build();

        Borrower borrower = Borrower.builder()
                .borrowerId(1)
                .name("name")
                .email("email").build();

        BookIssue bookIssue = BookIssue.builder()
                .issueId(1)
                .book(book)
                .borrower(borrower)
                .issueDate(LocalDate.now())
                .issueStatus(BookIssueStatusConstants.STATUS_ISSUED).build();

        Mockito.when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        Mockito.when(borrowerRepository.findById(1)).thenReturn(Optional.of(borrower));
        Mockito.when(bookIssueRepository.findByBorrowerAndBook(borrower, book)).thenReturn(bookIssue);
        Mockito.when(bookIssueRepository.save(Mockito.any())).thenReturn(bookIssue);
        Mockito.when(bookRepository.save(Mockito.any())).thenReturn(book);

        bookIssueService.returnBook(bookIssueOrReturnRequest);

        Mockito.verify(bookRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(bookIssueRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void returnBook_2() {
        BookIssueOrReturnRequest bookIssueOrReturnRequest = new BookIssueOrReturnRequest();
        bookIssueOrReturnRequest.setBookId(1);
        bookIssueOrReturnRequest.setBorrowerId(1);

        Borrower borrower = Borrower.builder()
                .borrowerId(1)
                .name("name")
                .email("email").build();

        Mockito.when(bookRepository.findById(1)).thenReturn(Optional.empty());
        Mockito.when(borrowerRepository.findById(1)).thenReturn(Optional.of(borrower));

        Assertions.assertThrows(BookIssueException.class, () -> {
            bookIssueService.returnBook(bookIssueOrReturnRequest);
        });
    }

    @Test
    void returnBook_3() {
        BookIssueOrReturnRequest bookIssueOrReturnRequest = new BookIssueOrReturnRequest();
        bookIssueOrReturnRequest.setBookId(1);
        bookIssueOrReturnRequest.setBorrowerId(1);

        Book book = Book.builder()
                .bookId(1)
                .title("title")
                .author("author")
                .isbnCode("isbn")
                .totalCopies(1)
                .availableCopies(1).build();

        Mockito.when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        Mockito.when(borrowerRepository.findById(1)).thenReturn(Optional.empty());

        Assertions.assertThrows(BookIssueException.class, () -> {
            bookIssueService.returnBook(bookIssueOrReturnRequest);
        });
    }

    @Test
    void returnBook_4() {
        BookIssueOrReturnRequest bookIssueOrReturnRequest = new BookIssueOrReturnRequest();
        bookIssueOrReturnRequest.setBookId(1);
        bookIssueOrReturnRequest.setBorrowerId(1);

        Book book = Book.builder()
                .bookId(1)
                .title("title")
                .author("author")
                .isbnCode("isbn")
                .totalCopies(1)
                .availableCopies(1).build();

        Borrower borrower = Borrower.builder()
                .borrowerId(1)
                .name("name")
                .email("email").build();

        Mockito.when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        Mockito.when(borrowerRepository.findById(1)).thenReturn(Optional.of(borrower));
        Mockito.when(bookIssueRepository.findByBorrowerAndBook(borrower, book)).thenReturn(null);

        Assertions.assertThrows(BookIssueException.class, () -> {
            bookIssueService.returnBook(bookIssueOrReturnRequest);
        });
    }

    @Test
    void returnBook_5() {
        BookIssueOrReturnRequest bookIssueOrReturnRequest = new BookIssueOrReturnRequest();
        bookIssueOrReturnRequest.setBookId(1);
        bookIssueOrReturnRequest.setBorrowerId(1);

        Book book = Book.builder()
                .bookId(1)
                .title("title")
                .author("author")
                .isbnCode("isbn")
                .totalCopies(1)
                .availableCopies(1).build();

        Borrower borrower = Borrower.builder()
                .borrowerId(1)
                .name("name")
                .email("email").build();

        BookIssue bookIssue = BookIssue.builder()
                .issueId(1)
                .book(book)
                .borrower(borrower)
                .issueDate(LocalDate.now())
                .returnDate(LocalDate.now())
                .issueStatus(BookIssueStatusConstants.STATUS_RETURNED).build();

        Mockito.when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        Mockito.when(borrowerRepository.findById(1)).thenReturn(Optional.of(borrower));
        Mockito.when(bookIssueRepository.findByBorrowerAndBook(borrower, book)).thenReturn(bookIssue);

        Assertions.assertThrows(BookIssueException.class, () -> {
            bookIssueService.returnBook(bookIssueOrReturnRequest);
        });
    }
}