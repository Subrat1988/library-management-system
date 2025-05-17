package org.lms.app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.flywaydb.core.internal.util.CollectionsUtils;
import org.lms.app.constants.ApplicationConstants;
import org.lms.app.entity.Book;
import org.lms.app.request.BookRegistrationRequest;
import org.lms.app.response.Response;
import org.lms.app.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
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

        Book book = bookService.registerBook(bookRegistrationRequest);
        response = new Response<>(ApplicationConstants.STATUS_SUCCESS, "Book registered successfully", book);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<Response<List<Book>>> getBooks() {
        List<Book> books = bookService.findBooks();
        Response response;

        if(CollectionUtils.isEmpty(books)) {
            response = new Response<>(ApplicationConstants.STATUS_FAILED, "No books found on Library Management System", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        } else {
            response = new Response<List<Book>>(ApplicationConstants.STATUS_SUCCESS, "Successfully fetched book records from Library Management System", books);
            return ResponseEntity.ok(response);
        }
    }
}
