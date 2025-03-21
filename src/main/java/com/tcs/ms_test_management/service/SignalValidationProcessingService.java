package com.tcs.ms_test_management.service;


import com.tcs.ms_test_management.domain.dto.ResponseDto;
import com.tcs.ms_test_management.domain.dto.SignalValidationSuccessDto;
import com.tcs.ms_test_management.domain.dto.TroubleTicketManagemenDto;

public interface SignalValidationProcessingService {
    ResponseDto processSignalValidation(SignalValidationSuccessDto signalValidationSuccessDto,
                                        String authorizationToken);
}
