package org.lms.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "book_id")
    private int bookId;

    @Column(name = "ISBN_Code")
    private String isbnCode;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "total_copies")
    private int totalCopies;

    @Column(name = "available_copies")
    private int availableCopies;
}
