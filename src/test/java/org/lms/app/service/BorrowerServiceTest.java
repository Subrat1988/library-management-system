package org.lms.app.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lms.app.entity.Borrower;
import org.lms.app.exceptions.BorrowerAlreadyExistsException;
import org.lms.app.repository.BorrowerRepository;
import org.lms.app.request.BookRegistrationRequest;
import org.lms.app.request.BorrowerRegistrationRequest;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BorrowerServiceTest {
    private BorrowerService borrowerService;
    private BorrowerRepository borrowerRepository;

    @BeforeEach
    void setup() {
        borrowerRepository = Mockito.mock(BorrowerRepository.class);
        borrowerService = new BorrowerService(borrowerRepository);
    }
    @Test
    void registerBorrower() {
        BorrowerRegistrationRequest borrowerRegistrationRequest = new BorrowerRegistrationRequest();
        borrowerRegistrationRequest.setName("dummy name");
        borrowerRegistrationRequest.setEmailId("dummy email");

        Borrower borrower = Borrower.builder()
                .borrowerId(1)
                .name("dummy name")
                .email("dummy email").build();

        Mockito.when(borrowerRepository.findByemail(Mockito.any())).thenReturn(null);
        Mockito.when(borrowerRepository.save(Mockito.any())).thenReturn(borrower);

        borrowerService.registerBorrower(borrowerRegistrationRequest);

        Mockito.verify(borrowerRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void registerBorrower_2() {
        BorrowerRegistrationRequest borrowerRegistrationRequest = new BorrowerRegistrationRequest();
        borrowerRegistrationRequest.setName("dummy name");
        borrowerRegistrationRequest.setEmailId("dummy email");

        Borrower borrower = Borrower.builder()
                .borrowerId(1)
                .name("dummy name")
                .email("dummy email").build();

        Mockito.when(borrowerRepository.findByemail(Mockito.any())).thenReturn(borrower);

        Assertions.assertThrows(BorrowerAlreadyExistsException.class, () -> {
            borrowerService.registerBorrower(borrowerRegistrationRequest);
        });

    }

    @Test
    void findBorrower() {
        Borrower borrower = Borrower.builder()
                .borrowerId(1)
                .name("dummy name")
                .email("dummy email").build();

        Mockito.when(borrowerRepository.findById(1)).thenReturn(Optional.of(borrower));

        Borrower borrowerObj = borrowerService.findBorrower(1);

        Assertions.assertEquals("dummy name", borrowerObj.getName());
        Assertions.assertEquals("dummy email", borrowerObj.getEmail());
    }
}