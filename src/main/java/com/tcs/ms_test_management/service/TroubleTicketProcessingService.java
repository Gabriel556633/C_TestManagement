package com.tcs.ms_test_management.service;

import com.tcs.ms_test_management.domain.dto.CreateTicketResponseDto;
import com.tcs.ms_test_management.domain.dto.ResponseDto;
import com.tcs.ms_test_management.domain.dto.TroubleTicketManagemenDto;

public interface TroubleTicketProcessingService {
    ResponseDto processTickets(TroubleTicketManagemenDto troubleTicketManagemenDto,String authorizationToken);
}
