package org.lms.app.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookRegistrationRequest {

    @NotBlank(message = "Book ISBN Code cannot be empty")
    private String isbnCode;

    @NotBlank(message = "Book Title cannot be empty")
    private String title;

    @NotBlank(message = "Book Author cannot be empty")
    private String author;
}
