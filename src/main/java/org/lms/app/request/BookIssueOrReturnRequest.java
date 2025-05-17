package org.lms.app.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookIssueOrReturnRequest {
    @NotNull(message = "Borrower Id cannot be empty")
    private Integer borrowerId;

    @NotNull(message = "Book Id cannot be empty")
    private Integer bookId;
}
