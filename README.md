# Library-Management-System
This is a simple Library Management System which enabled/allows user to register new books, register new borrower, perform actions on behalf of borrower to borrow a book.

# Problem Statement
The system should allow the following actions:
* The API should allow API user to perform the following actions:
  1. Register a new borrower to the library.
  2. Register a new book to the library.
  3. Get a list of all books in the library.
* The API should allow API user to perform the following actions on behalf of a borrower:
  1. Borrow a book with a particular book id (refer Book in Data Models).
  2. Return a borrowed book.

# Requirement Changes (Assumptions)
* As we do allow 2 books with the same ISBN numbers must have the same title and same author as Multiple copies of books with same ISBN number on the system. Then Multiple Members (Borrowers) can borrow the same book (i.e. Same Book Id) if copies available.

# ER Diagram

![LMS](https://github.com/user-attachments/assets/1831fd4c-1be3-43b0-bbb4-1ee5ca1784cc)

# Future Improvments
* Implement Reminder System to send reminder to Borrowers if the book is not returned with in the timeline (i.e. This can be implemented).
* Implement Buisiness logic to impose fine on borrowers if the book is not returned on time or damaged/lost.
* Implement Book Reserve for future date facility for the user who are not able to borrow a book due to it's non-availability.
