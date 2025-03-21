package com.tcs.ms_test_management.service.impl;

import com.tcs.ms_test_management.domain.dto.CreateTicketRequestDto;
import com.tcs.ms_test_management.domain.dto.CreateTicketResponseDto;
import com.tcs.ms_test_management.repository.CreateTicketRepository;
import com.tcs.ms_test_management.service.CreateTicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTicketServiceImpl implements CreateTicketService {

    private static final Logger logger = LoggerFactory.getLogger(CreateTicketServiceImpl.class);
    private final CreateTicketRepository createTicketRepository;

    @Autowired
    public CreateTicketServiceImpl(CreateTicketRepository createTicketRepository) {
        this.createTicketRepository = createTicketRepository;
    }

    @Override
    public CreateTicketResponseDto createTicket(CreateTicketRequestDto request, String authorizationToken) {
        try {
            return createTicketRepository.createTicket(request, authorizationToken);
        } catch (RuntimeException e) {
            logger.error("Error while creating ticket", e);
            throw e; // Propaga la excepción al controlador
        }
    }
}
