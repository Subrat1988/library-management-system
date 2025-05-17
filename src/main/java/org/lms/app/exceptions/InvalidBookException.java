package org.lms.app.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvalidBookException extends RuntimeException {

    private String message;
}
