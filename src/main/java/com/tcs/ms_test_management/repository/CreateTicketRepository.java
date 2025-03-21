package com.tcs.ms_test_management.repository;



import com.tcs.ms_test_management.domain.dto.CreateTicketRequestDto;
import com.tcs.ms_test_management.domain.dto.CreateTicketResponseDto;

public interface CreateTicketRepository {
    CreateTicketResponseDto createTicket(CreateTicketRequestDto request, String authorizationToken);
}
