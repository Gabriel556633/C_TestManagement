package com.tcs.ms_test_management.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.ms_test_management.domain.dto.CreateTicketRequestDto;
import com.tcs.ms_test_management.domain.dto.CreateTicketResponseDto;
import com.tcs.ms_test_management.domain.dto.ErrorDto;
import com.tcs.ms_test_management.repository.CreateTicketRepository;
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

@Repository
public class CreateTicketRepositoryImpl implements CreateTicketRepository {

    private static final Logger logger = LoggerFactory.getLogger(CreateTicketRepositoryImpl.class);
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public CreateTicketRepositoryImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public CreateTicketResponseDto createTicket(CreateTicketRequestDto request, String authorizationToken) {
        try {
            String url = "http://localhost:8181/troubleTicketManagement/v5/troubleTicket";

            HttpHeaders headers = new HttpHeaders();
            headers.add("Accept", "application/json");
            headers.add("Authorization", "Bearer " + authorizationToken);

            HttpEntity<CreateTicketRequestDto> entity = new HttpEntity<>(request, headers);

            logger.debug("Creating ticket with URL: {}", url);
            ResponseEntity<CreateTicketResponseDto> response = restTemplate.exchange(url, HttpMethod.POST, entity, CreateTicketResponseDto.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            logger.error("Client error while creating ticket", e);
            throw new RuntimeException(handleClientError(e));
        } catch (HttpServerErrorException e) {
            logger.error("Server error while creating ticket", e);
            throw new RuntimeException(handleServerError(e));
        } catch (ResourceAccessException e) {
            logger.error("Timeout or connection error while creating ticket", e);
            throw new RuntimeException(handleTimeoutError());
        } catch (Exception e) {
            logger.error("Unexpected error while creating ticket", e);
            throw new RuntimeException(handleUnexpectedError(e));
        }
    }

    private String handleClientError(HttpClientErrorException e) {
        try {
            ErrorDto errorResponse = objectMapper.readValue(e.getResponseBodyAsString(), ErrorDto.class);
            logger.error("Client error response: {}", errorResponse);
            return errorResponse.getMessage();
        } catch (Exception ex) {
            logger.error("Error parsing client error response", ex);
            return "Error parsing client error response.";
        }
    }

    private String handleServerError(HttpServerErrorException e) {
        try {
            ErrorDto errorResponse = objectMapper.readValue(e.getResponseBodyAsString(), ErrorDto.class);
            logger.error("Server error response: {}", errorResponse);
            return errorResponse.getMessage();
        } catch (Exception ex) {
            logger.error("Error parsing server error response", ex);
            return "Error parsing server error response.";
        }
    }

    private String handleTimeoutError() {
        ErrorDto errorResponse = new ErrorDto();
        errorResponse.setCode("408");
        errorResponse.setReason("Request Timeout");
        errorResponse.setMessage("Timeout or connection error occurred...");
        errorResponse.setStatus("REQUEST_TIMEOUT");
        logger.error("Timeout error response: {}", errorResponse);
        return errorResponse.getMessage();
    }

    private String handleUnexpectedError(Exception e) {
        ErrorDto errorResponse = new ErrorDto();
        errorResponse.setCode("500");
        errorResponse.setReason("Internal Server Error");
        errorResponse.setMessage("An unexpected error occurred.");
        errorResponse.setStatus("INTERNAL_SERVER_ERROR");
        logger.error("Unexpected error response: {}", errorResponse);
        return errorResponse.getMessage();
    }
}
