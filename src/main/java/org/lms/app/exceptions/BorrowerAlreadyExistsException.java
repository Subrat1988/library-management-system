package org.lms.app.exceptions;

import lombok.AllArgsConstructor;

public class BorrowerAlreadyExistsException extends RuntimeException{
    public BorrowerAlreadyExistsException(String message) {
        super(message);
    }
}
