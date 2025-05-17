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

# Tech Stack & Tools

**Java Version:** JDK 21
**IDE:** Idea IntelliJ Community Edition
**Build Tool/Package Manager:** Gradle
**SQL(mysql):** SQL database has been used as we've strong relationships between different entities, Data Consitency and Data Integrity is a major factor during the book issue process, registering new books and borrowers.

# Requirement Changes (Assumptions)
* As we do allow 2 books with the same ISBN numbers must have the same title and same author as Multiple copies of books with same ISBN number on the system. Then Multiple Members (Borrowers) can borrow the same book (i.e. Same Book Id) if copies available.

# ER Diagram

![LMS](https://github.com/user-attachments/assets/1831fd4c-1be3-43b0-bbb4-1ee5ca1784cc)

# API Documentation
### Register Book  
This API is used for registering a new or existing book's additional copy to the Library Management System.  

**API:** http://localhost:8080/libraryManagementSystem/v1/books  
**Request Method:** POST  
**Sample Request Body:**  
```json
{
    "isbnCode":"5623-4587-1245-9865",
    "title":"Headfirst Java",
    "author":"Eric Freeman"
}
```

**Sample Response(Success):**   
```json
{
    "status": "Success",
    "message": "Book registered successfully",
    "data": {
        "bookId": 0,
        "isbnCode": "5623-4587-1245-9865",
        "title": "Headfirst Java",
        "author": "Eric Freeman",
        "totalCopies": 1,
        "availableCopies": 1
    }
}
```

### Get all the Books  
This API is used for getting/fetching all the books registered on Library Management System.  

**API:** http://localhost:8080/libraryManagementSystem/v1/books  
**Request Method:** GET  
**Sample Response(Success):**   
```json
{
    "status": "Success",
    "message": "Successfully fetched books record from Library Management System",
    "data": [
        {
            "bookId": 3,
            "isbnCode": "5623-4587-1245-9865",
            "title": "Headfirst Java",
            "author": "Eric Freeman",
            "totalCopies": 1,
            "availableCopies": 1
        }
    ]
}
```

### Register Borrower  
This API is used for registering a new borrower to the Library Management System.  

**API:** http://localhost:8080/libraryManagementSystem/v1/users  
**Request Method:** POST  
**Sample Request Body:**  
```json
{
    "name":"Subrat Kumar Patra",
    "emailId": "subrat@gmail.com"
}
```

**Sample Response(Success):**   
```json
{
    "status": "Success",
    "message": "Borrower registered successfully",
    "data": {
        "borrowerId": 0,
        "name": "Subrat Kumar Patra",
        "email": "subrat@gmail.com"
    }
}
```

### Issue Book  
This API is used for registering a new borrower to the Library Management System.  

**API:** http://localhost:8080/libraryManagementSystem/v1/users  
**Request Method:** POST  
**Sample Request Body:**  
```json
{
    "borrowerId" : 1,
    "bookId" : "3"
}
```

**Sample Response(Success):**   
```json
{
    "status": "Success",
    "message": "Book issued successfully",
    "data": "Book with id 3 successfully borrowed by Borrower with id 1 with Book Issue id 0"
}
```

### Return Book  
This API is used for registering a new borrower to the Library Management System.  

**API:** http://localhost:8080/libraryManagementSystem/v1/users  
**Request Method:** POST  
**Sample Request Body:**  
```json
{
    "borrowerId" : 1,
    "bookId" : "3"
}
```

**Sample Response(Success):**   
```json
{
    "status": "Success",
    "message": "Book returned successfully",
    "data": "Book with id 3 successfully returned by Borrower with id 1 with Book Issue id 1"
}
```

# Local Testing
The application needs MySql database. We can use docker to start an instance if MySql database on your local to test the application.  
### Docker Command (mysql):  
docker run --name lms-mysql-database -p 3306:3306 -e MYSQL_ROOT_PASSWORD=rootPassword -e MYSQL_DATABASE=lms-database -e MYSQL_USER=lms-user -e MYSQL_PASSWORD=lmsPassword -d mysql:9.3.0

# Future Improvments
* Implement Reminder System to send reminder to Borrowers if the book is not returned with in the return date.
* Implement Buisiness logic to impose fine on borrowers if the book is not returned on time or damaged/lost.
* Implement Book Reserve for future date feature for the users who are not able to borrow a book due to it's non-availability.
