
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
**Frameworks:** SpringBoot (Spring Web, Spring Data JPA), Flyway (For database migration)  
**Build Tool/Package Manager:** Gradle  
**Code Coverage:** Jacoco  
**SQL(mysql):** SQL database has been used as we've strong relationships between different entities, Data Consitency and Data Integrity is a major factor during the book issue process, registering new books and borrowers.  

# Swagger-UI Integration  
Swagger-UI has been integrated with the application, Once the application is up and running, we can use the below mentioned url to acces API specification.  
### http://localhost:8080/swagger-ui/index.html#/  
![Swagger-UI](https://github.com/user-attachments/assets/a7656baf-5409-4709-a261-068a7d979226)


# Requirement Changes (Assumptions)
* As we do allow 2 books with the same ISBN numbers having the same title and same author. We can add this as multiple copies of books with same ISBN number on the system. Then Multiple Members (Borrowers) can borrow the same book (i.e. Same Book Id) if copies available.
* No 2 user/borrowers can have same email id.

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
This API is used for borrowing a book by borrower via the Library Management System.  

**API:** http://localhost:8080/libraryManagementSystem/v1/borrow  
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
This API is used for returning a book to the Library Management System.  

**API:** http://localhost:8080/libraryManagementSystem/v1/return  
**Request Method:** PATCH  
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
The application needs MySql database. We can use docker to start an instance of MySql database on our local to test the application.  
### Create a network(Docker):  
docker network create lms-network

### Docker Command (mysql):  
docker run --name lms-mysql-database --network lms-network -p 3306:3306 -e MYSQL_ROOT_PASSWORD=rootPassword -e MYSQL_DATABASE=lms-database -e MYSQL_USER=lms-user -e MYSQL_PASSWORD=lmsPassword -d mysql:9.3.0

# Containerization  
Sample Dockerfile has been added to the project. Which will create the docker image for the library-management-system application with openjdk:21 base image.  
**Command(Build Docker Image):** docker build . -t lms-app  
**Command(Run):** docker run --name lms-app --network lms-network -p 8080:8080 -e spring.datasource.url=jdbc:mysql://lms-mysql-database:3306/lms-database?allowPublicKeyRetrieval=true -d lms-app
***Note:*** The mysql container and application container should be started on the same network.

**or**

**docker-compose.yml** file has been created to run the mysql database and library-management-system service on docker.  
**Command:** docker-compose up

# Profiles
There are 5 different application.yml file have been created for 5 profiles.  
Default (Local), dev, qa, uat and prod.  
To start the application in any specific profile, the below command should be used with the specific profile. If the profile is not mentioned the application will start with default profile.  
**./gradlew bootRun --args='--spring.profiles.active=dev'**  

# Future Improvments
* Implement Reminder System to send reminder to Borrowers if the book is not returned with in the return date.
* Implement Buisiness logic to impose fine on borrowers if the book is not returned on time or damaged/lost.
* Implement Book Reserve for future date feature for the users who are not able to borrow a book due to it's non-availability.
