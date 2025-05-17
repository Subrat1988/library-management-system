package org.lms.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lms.app.entity.Book;
import org.lms.app.exceptions.InvalidBookException;
import org.lms.app.repository.BookRepository;
import org.lms.app.request.BookRegistrationRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book registerBook(BookRegistrationRequest bookRegistrationRequest) {
        Book book = bookRepository.findByisbnCode(bookRegistrationRequest.getIsbnCode());
        if(null != book) {
            /*  2 books with the same ISBN number must have the same title and same author  */
            if(book.getAuthor().equals(bookRegistrationRequest.getAuthor())
                    && book.getTitle().equals(bookRegistrationRequest.getTitle())) {
                book.setTotalCopies(book.getTotalCopies() + 1);
            } else {
                log.warn("A book with ISBN {} already exists with different title or author", bookRegistrationRequest.getIsbnCode());
                throw new InvalidBookException("Invalid Book");
            }
        } else {
            book = Book.builder().isbnCode(bookRegistrationRequest.getIsbnCode()).title(bookRegistrationRequest.getTitle())
                    .author(bookRegistrationRequest.getAuthor()).totalCopies(1).availableCopies(1).build();
        }

        book = bookRepository.save(book);
        log.info("Book with ISBN {} has been successfully registered", book.getIsbnCode());

        return book;
    }

    public List<Book> findBooks() {
        return null;
    }
}
