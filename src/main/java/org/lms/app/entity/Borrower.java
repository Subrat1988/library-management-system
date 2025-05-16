package org.lms.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Borrower")
public class Borrower {

    @Id
    @Column(name = "borrower_id")
    private int borrowerId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;
}
