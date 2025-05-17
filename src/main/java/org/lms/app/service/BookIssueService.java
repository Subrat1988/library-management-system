package org.lms.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lms.app.constants.BookIssueStatusConstants;
import org.lms.app.entity.Book;
import org.lms.app.entity.BookIssue;
import org.lms.app.entity.Borrower;
import org.lms.app.exceptions.BookIssueException;
import org.lms.app.repository.BookIssueRepository;
import org.lms.app.repository.BookRepository;
import org.lms.app.repository.BorrowerRepository;
import org.lms.app.request.BookIssueOrReturnRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookIssueService {
    private final BookIssueRepository bookIssueRepository;

    private final BookRepository bookRepository;

    private final BorrowerRepository borrowerRepository;

    public String issueBook(BookIssueOrReturnRequest bookIssueOrReturnRequest) {
        Book book = bookRepository.findById(bookIssueOrReturnRequest.getBookId()).orElse(null);
        Borrower borrower = borrowerRepository.findById(bookIssueOrReturnRequest.getBorrowerId()).orElse(null);

        if(null == book) {
            throw new BookIssueException(String.format("Book with id %s not found on Library Management System", bookIssueOrReturnRequest.getBookId()));
        }

        if(null == borrower) {
            throw new BookIssueException(String.format("Borrower with id %s not found on Library Management System", bookIssueOrReturnRequest.getBorrowerId()));
        }

        if(book.getAvailableCopies() == 0) {
            throw new BookIssueException(String.format("No copies available for the book %s to borrow", book.getTitle()));
        }

        BookIssue bookIssue = new BookIssue();
        bookIssue.setBook(book);
        bookIssue.setBorrower(borrower);
        bookIssue.setIssueDate(LocalDate.now());
        bookIssue.setIssueStatus(BookIssueStatusConstants.STATUS_ISSUED);

        bookIssue = bookIssueRepository.saveAndFlush(bookIssue);
        log.info("Book with id {} successfully borrowed by Borrower with id {} with Book Issue id {}",
                book.getBookId(), borrower.getBorrowerId(), bookIssue.getIssueId());

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        book = bookRepository.saveAndFlush(book);

        return String.format("Book with id %s successfully borrowed by Borrower with id %s with Book Issue id %s",
                book.getBookId(), borrower.getBorrowerId(), bookIssue.getIssueId());
    }

    public String returnBook(BookIssueOrReturnRequest bookIssueOrReturnRequest) {
        Book book = bookRepository.findById(bookIssueOrReturnRequest.getBookId()).orElse(null);
        Borrower borrower = borrowerRepository.findById(bookIssueOrReturnRequest.getBorrowerId()).orElse(null);

        if(null == book) {
            throw new BookIssueException(String.format("Invalid Book: Book with id %s not found on Library Management System",
                    bookIssueOrReturnRequest.getBookId()));
        }

        if(null == borrower) {
            throw new BookIssueException(String.format("Invalid Borrower: Borrower with id %s not found on Library Management System",
                    bookIssueOrReturnRequest.getBorrowerId()));
        }

        BookIssue bookIssue = bookIssueRepository.findByBorrowerAndBook(borrower, book);

        if(null == bookIssue) {
            throw new BookIssueException(String.format("No book issue record found for Book Id %s and Borrower Id %s",
                    book.getBookId(), borrower.getBorrowerId()));
        }

        if(null != bookIssue.getReturnDate()) {
            throw new BookIssueException(String.format("Book with id %s already returned on %s by borrower %s",
                    book.getBookId(), bookIssue.getReturnDate().toString(), borrower.getName()));
        }

        bookIssue.setReturnDate(LocalDate.now());
        bookIssue.setIssueStatus(BookIssueStatusConstants.STATUS_RETURNED);

        bookIssue = bookIssueRepository.saveAndFlush(bookIssue);

        book.setAvailableCopies(book.getAvailableCopies()+1);
        log.info("Book with id {} successfully returned by Borrower with id {} with Book Issue id {}",
                book.getBookId(), borrower.getBorrowerId(), bookIssue.getIssueId());

        book = bookRepository.saveAndFlush(book);

        return String.format("Book with id %s successfully returned by Borrower with id %s with Book Issue id %s",
                book.getBookId(), borrower.getBorrowerId(), bookIssue.getIssueId());
    }
}
