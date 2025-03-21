package com.tcs.ms_test_management.service;

import com.tcs.ms_test_management.domain.dto.CreateTicketRequestDto;
import com.tcs.ms_test_management.domain.dto.CreateTicketResponseDto;

public interface CreateTicketService {
    CreateTicketResponseDto createTicket(CreateTicketRequestDto request, String authorizationToken);
}
