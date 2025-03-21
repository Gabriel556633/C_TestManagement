package com.tcs.ms_test_management.service;

import com.tcs.ms_test_management.domain.dto.SignalValidationSuccessDto;
import java.util.Optional;

public interface SignalValidationService {
    Optional<SignalValidationSuccessDto> getSignalValidation(String serviceNumber, String resourceSpecification, String authorizationToken);
}