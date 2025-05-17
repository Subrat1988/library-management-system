package org.lms.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lms.app.entity.Borrower;
import org.lms.app.exceptions.BorrowerAlreadyExistsException;
import org.lms.app.repository.BorrowerRepository;
import org.lms.app.request.BorrowerRegistrationRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BorrowerService {
    private final BorrowerRepository borrowerRepository;

    public Borrower registerBorrower(BorrowerRegistrationRequest borrowerRegistrationRequest) {
        Borrower borrower = borrowerRepository.findByemail(borrowerRegistrationRequest.getEmailId());

        if(null != borrower) {
            throw new BorrowerAlreadyExistsException(String.format("Borrower with email %s already exists on Library Management System"));
        } else {
            borrower = Borrower.builder()
                    .name(borrowerRegistrationRequest.getName())
                    .email(borrowerRegistrationRequest.getEmailId()).build();

            borrower = borrowerRepository.save(borrower);
        }
        log.info("Borrower with email {} registered successfully", borrower.getEmail());

        return borrower;
    }

    public Borrower findBorrower(int borrowerId) {
        return borrowerRepository.findById(borrowerId).orElse(null);
    }
}
