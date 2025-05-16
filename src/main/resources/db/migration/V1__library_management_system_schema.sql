CREATE TABLE Book (
   book_id int NOT NULL AUTO_INCREMENT,
   ISBN_Code varchar(255) NOT NULL,
   title varchar(255) NOT NULL,
   author varchar(255) NOT NULL,
   total_copies int,
   available_copies int,
   PRIMARY KEY (book_id)
);

CREATE TABLE Borrower (
    borrower_id int NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    email varchar(255) NOT NULL
);

CREATE TABLE Book_Issue (
    issue_id int NOT NULL AUTO_INCREMENT,
    book_id int,
    borrower_id int,
    issue_date DATE,
    return_date DATE,
    issue_status varchar(255),
    PRIMARY KEY (issue_id),
    FOREIGN KEY (book_id) REFERENCES Book(book_id),
    FOREIGN KEY (borrower_id) REFERENCES Borrower(borrower_id)
);