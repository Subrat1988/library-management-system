package org.lms.app.response;

import lombok.Data;

@Data
public class Response<T> {
    private String status;
    private String message;
    private T data;
}
