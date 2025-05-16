package org.lms.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.lms.app.entity.Borrower;
import org.lms.app.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/libraryManagementSystem/v1")
public class BorrowerController {

    @RequestMapping(value = "/users", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<Response<Borrower>> register(@RequestBody Borrower borrower) {
        return null;
    }
}
