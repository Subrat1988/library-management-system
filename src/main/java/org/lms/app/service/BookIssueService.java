package org.lms.app.service;

import lombok.RequiredArgsConstructor;
import org.lms.app.repository.BookIssueRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookIssueService {
    private final BookIssueRepository bookIssueRepository;
}
