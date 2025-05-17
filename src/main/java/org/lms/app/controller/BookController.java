package org.lms.app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lms.app.constants.ApplicationConstants;
import org.lms.app.entity.Book;
import org.lms.app.request.BookRegistrationRequest;
import org.lms.app.response.Response;
import org.lms.app.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/libraryManagementSystem/v1")
public class BookController {
    private final BookService bookService;

    @RequestMapping(value = "/books", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<Response<Book>> register(@Valid @RequestBody BookRegistrationRequest bookRegistrationRequest) {
        Response response;

        try {
            Book book = bookService.registerBook(bookRegistrationRequest);
            response = new Response<>(ApplicationConstants.STATUS_SUCCESS, "Book registered successfully", book);
        } catch (Exception exception) {
            log.error("Exception occurred while registering the book with ISBN {}", bookRegistrationRequest.getIsbnCode(), exception);
            response = new Response(ApplicationConstants.STATUS_FAILED, "Book registration failed", null);
        }

        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<Response<List<Book>>> getBooks() {
        return null;
    }
}
