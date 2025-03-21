package com.tcs.ms_test_management.repository;

import com.tcs.ms_test_management.domain.dto.TroubleTicketManagemenDto;

import java.util.Optional;

public interface TroubleTicketManagemenRepository {
    TroubleTicketManagemenDto consultarTicket(String id, String idType, String status, String authorizationToken);
}
