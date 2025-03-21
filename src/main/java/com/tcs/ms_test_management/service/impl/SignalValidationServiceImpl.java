package com.tcs.ms_test_management.service.impl;

import com.tcs.ms_test_management.domain.dto.SignalValidationSuccessDto;
import com.tcs.ms_test_management.repository.SignalValidationRepository;
import com.tcs.ms_test_management.service.SignalValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SignalValidationServiceImpl implements SignalValidationService {

    private static final Logger logger = LoggerFactory.getLogger(SignalValidationServiceImpl.class);
    private final SignalValidationRepository signalValidationRepository;

    @Autowired
    public SignalValidationServiceImpl(SignalValidationRepository signalValidationRepository) {
        this.signalValidationRepository = signalValidationRepository;
    }

    @Override
    public Optional<SignalValidationSuccessDto> getSignalValidation(String serviceNumber, String resourceSpecification, String authorizationToken) {
        try {
            logger.debug("Consultando signalValidation con serviceNumber: {}, resourceSpecification: {}", serviceNumber, resourceSpecification);
            return signalValidationRepository.getSignalValidation(serviceNumber, resourceSpecification, authorizationToken);
        } catch (RuntimeException e) {
            logger.error("Error while consulting signalValidation with serviceNumber: {}", serviceNumber, e);
            throw e; // Propaga la excepción al controlador
        }
    }
}