package com.tcs.ms_test_management.service.impl;

import com.tcs.ms_test_management.domain.dto.TroubleTicketManagemenDto;
import com.tcs.ms_test_management.repository.TroubleTicketManagemenRepository;
import com.tcs.ms_test_management.service.TroubleTicketManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TroubleTicketManagementServiceImpl implements TroubleTicketManagementService {

    private static final Logger logger = LoggerFactory.getLogger(TroubleTicketManagementServiceImpl.class);
    private final TroubleTicketManagemenRepository ticketExternalRepository;

    @Autowired
    public TroubleTicketManagementServiceImpl(TroubleTicketManagemenRepository ticketExternalRepository) {
        this.ticketExternalRepository = ticketExternalRepository;
    }

    @Override
    public TroubleTicketManagemenDto consultarTicket(String id, String idType, String status, String authorizationToken) {
        try {
            return ticketExternalRepository.consultarTicket(id, idType, status, authorizationToken);
        } catch (RuntimeException e) {
            logger.error("Error while consulting ticket with ID: {}", id, e);
            throw e; // Propaga la excepción al controlador
        }
    }
}