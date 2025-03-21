package com.tcs.ms_test_management.service;

import com.tcs.ms_test_management.domain.dto.TroubleTicketManagemenDto;

import java.util.Optional;

public interface TroubleTicketManagementService {
    TroubleTicketManagemenDto consultarTicket(String id, String idType, String status, String authorizationToken);
}
