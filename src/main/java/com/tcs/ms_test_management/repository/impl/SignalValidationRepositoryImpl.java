package com.tcs.ms_test_management.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.ms_test_management.domain.dto.SignalValidationSuccessDto;
import com.tcs.ms_test_management.domain.dto.SignalValidationErrorDto;
import com.tcs.ms_test_management.repository.SignalValidationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Repository
public class SignalValidationRepositoryImpl implements SignalValidationRepository {

    private static final Logger logger = LoggerFactory.getLogger(SignalValidationRepositoryImpl.class);
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public SignalValidationRepositoryImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public Optional<SignalValidationSuccessDto> getSignalValidation(String serviceNumber, String resourceSpecification, String authorizationToken) {
        try {
            String url = "http://localhost:8181/servicetestmanagement/v4/serviceTest/" + serviceNumber +
                    "?resourceSpecification=" + resourceSpecification;

            HttpHeaders headers = new HttpHeaders();
            headers.add("Accept-Language", "<string>");
            headers.add("Accept", "application/json");
            headers.add("Authorization", "Bearer " + authorizationToken);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            logger.debug("Consultando signalValidation con URL: {}", url);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            logger.debug("Respuesta del servidor: {}", response.getBody());

            SignalValidationSuccessDto successDto = objectMapper.readValue(response.getBody(), SignalValidationSuccessDto.class);
            return Optional.ofNullable(successDto);
        } catch (HttpClientErrorException e) {
            logger.error("Client error while consulting signalValidation with serviceNumber: {}", serviceNumber, e);
            throw new RuntimeException(handleClientError(e));
        } catch (HttpServerErrorException e) {
            logger.error("Server error while consulting signalValidation with serviceNumber: {}", serviceNumber, e);
            throw new RuntimeException(handleServerError(e));
        } catch (ResourceAccessException e) {
            logger.error("Timeout or connection error while consulting signalValidation with serviceNumber: {}", serviceNumber, e);
            throw new RuntimeException(handleTimeoutError(serviceNumber));
        } catch (Exception e) {
            logger.error("Unexpected error while consulting signalValidation with serviceNumber: {}", serviceNumber, e);
            throw new RuntimeException(handleUnexpectedError(e, serviceNumber));
        }
    }

    private String handleClientError(HttpClientErrorException e) {
        try {
            SignalValidationErrorDto errorResponse = objectMapper.readValue(e.getResponseBodyAsString(), SignalValidationErrorDto.class);
            logger.error("Client error response: {}", errorResponse);
            return errorResponse.getMessage();
        } catch (Exception ex) {
            logger.error("Error parsing client error response", ex);
            return "Error parsing client error response.";
        }
    }

    private String handleServerError(HttpServerErrorException e) {
        try {
            SignalValidationErrorDto errorResponse = objectMapper.readValue(e.getResponseBodyAsString(), SignalValidationErrorDto.class);
            logger.error("Server error response: {}", errorResponse);
            return errorResponse.getMessage();
        } catch (Exception ex) {
            logger.error("Error parsing server error response", ex);
            return "Error parsing server error response.";
        }
    }

    private String handleTimeoutError(String serviceNumber) {
        SignalValidationErrorDto errorResponse = new SignalValidationErrorDto();
        errorResponse.setCode("408");
        errorResponse.setStatus("Request Timeout");
        errorResponse.setMessage("Timeout or connection error occurred.");
        errorResponse.setTransactionId(serviceNumber);
        logger.error("Timeout error response: {}", errorResponse);
        return errorResponse.getMessage();
    }

    private String handleUnexpectedError(Exception e, String serviceNumber) {
        SignalValidationErrorDto errorResponse = new SignalValidationErrorDto();
        errorResponse.setCode("500");
        errorResponse.setStatus("Internal Server Error");
        errorResponse.setMessage("An unexpected error occurred.");
        errorResponse.setTransactionId(serviceNumber);
        logger.error("Unexpected error response: {}", errorResponse);
        return errorResponse.getMessage();
    }
}