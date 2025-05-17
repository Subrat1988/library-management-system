package org.lms.app.exceptions;

import lombok.AllArgsConstructor;

public class InvalidBookException extends RuntimeException {
    public InvalidBookException(String message) {
        super(message);
    }
}
