package com.tcs.ms_test_management.repository;

import com.tcs.ms_test_management.domain.dto.SignalValidationSuccessDto;
import java.util.Optional;

public interface SignalValidationRepository {
    Optional<SignalValidationSuccessDto> getSignalValidation(String serviceNumber, String resourceSpecification, String authorizationToken);
}