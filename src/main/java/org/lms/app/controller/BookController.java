package org.lms.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.lms.app.entity.Book;
import org.lms.app.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/libraryManagementSystem/v1")
public class BookController {

    @RequestMapping(value = "/books", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<Response<Book>> register(@RequestBody Book book) {
        return null;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<Response<List<Book>>> getBooks() {
        return null;
    }
}
