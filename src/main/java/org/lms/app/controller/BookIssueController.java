package org.lms.app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lms.app.constants.ApplicationConstants;
import org.lms.app.request.BookIssueOrReturnRequest;
import org.lms.app.response.Response;
import org.lms.app.service.BookIssueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/libraryManagementSystem/v1")
public class BookIssueController {
    private final BookIssueService bookIssueService;

    @RequestMapping(value = "/borrow", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<Response<String>> borrowBook(@Valid @RequestBody BookIssueOrReturnRequest bookIssueOrReturnRequest) {

        String res = bookIssueService.issueBook(bookIssueOrReturnRequest);
        Response response = new Response<>(ApplicationConstants.STATUS_SUCCESS, "Book issued successfully", res);

        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/return", method = RequestMethod.PATCH, produces = {"application/json"})
    public ResponseEntity<Response<String>> returnBook(@Valid @RequestBody BookIssueOrReturnRequest bookIssueOrReturnRequest) {

        String res = bookIssueService.returnBook(bookIssueOrReturnRequest);
        Response response = new Response<>(ApplicationConstants.STATUS_SUCCESS, "Book returned successfully", res);

        return ResponseEntity.ok(response);
    }
}
