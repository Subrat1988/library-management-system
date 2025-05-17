package org.lms.app.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BorrowerAlreadyExistsException extends RuntimeException{
    private String message;
}
