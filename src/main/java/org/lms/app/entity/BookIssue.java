package org.lms.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@Entity
@Table(name = "Book_Issue")
@NoArgsConstructor
@AllArgsConstructor
public class BookIssue {
    @Id
    @Column(name = "issue_id")
    private int issueId;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "issue_status")
    private String issueStatus;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Borrower borrower;
}
