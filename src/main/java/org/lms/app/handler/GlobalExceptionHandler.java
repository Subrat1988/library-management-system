package org.lms.app.handler;

import org.lms.app.constants.ApplicationConstants;
import org.lms.app.exceptions.BookIssueException;
import org.lms.app.exceptions.BorrowerAlreadyExistsException;
import org.lms.app.exceptions.InvalidBookException;
import org.lms.app.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleValidationException(MethodArgumentNotValidException e) {
        Response response = new Response<>(ApplicationConstants.STATUS_FAILED, e.getMessage(), null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(BorrowerAlreadyExistsException.class)
    public ResponseEntity<Response> handleBorrowerAlreadyExistsException (BorrowerAlreadyExistsException borrowerAlreadyExistsException) {
        Response response = new Response<>(ApplicationConstants.STATUS_FAILED, borrowerAlreadyExistsException.getMessage(), null);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(InvalidBookException.class)
    public ResponseEntity<Response> handleInvalidBookException(InvalidBookException invalidBookException) {
        Response response = new Response(ApplicationConstants.STATUS_FAILED, invalidBookException.getMessage(), null);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(BookIssueException.class)
    public ResponseEntity<Response> handleBookIssueException(BookIssueException exception) {
        Response response = new Response(ApplicationConstants.STATUS_FAILED, exception.getMessage(), null);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
