package org.lms.app.repository;

import org.lms.app.entity.Book;
import org.lms.app.entity.BookIssue;
import org.lms.app.entity.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookIssueRepository extends JpaRepository<BookIssue, Integer> {

    public BookIssue findByBorrowerAndBook(Borrower borrower, Book book);
}