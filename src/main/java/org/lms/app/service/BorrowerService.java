package org.lms.app.service;

import lombok.RequiredArgsConstructor;
import org.lms.app.repository.BorrowerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BorrowerService {
    private final BorrowerRepository borrowerRepository;
}
