package org.lms.app.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookIssueRequest {
    @NotBlank(message = "Borrower Id cannot be empty")
    private String borrowerId;

    @NotBlank(message = "Book Id cannot be empty")
    private String bookId;
}
