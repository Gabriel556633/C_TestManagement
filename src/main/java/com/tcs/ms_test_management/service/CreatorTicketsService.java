package com.tcs.ms_test_management.service;

import com.tcs.ms_test_management.domain.dto.*;

import java.util.List;

public interface CreatorTicketsService {
    CreateTicketResponseDto creatorTicket(List<RptTicketRemedyDTO> remedies,
                                          String authorizationToken);
}
