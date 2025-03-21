package com.tcs.ms_test_management.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.ms_test_management.domain.dto.TroubleTicketManagemenDto;
import com.tcs.ms_test_management.domain.dto.ErrorDto;
import com.tcs.ms_test_management.repository.TroubleTicketManagemenRepository;
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
public class TroubleTicketManagemenRepositoryImpl implements TroubleTicketManagemenRepository {

    private static final Logger logger = LoggerFactory.getLogger(TroubleTicketManagemenRepositoryImpl.class);
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public TroubleTicketManagemenRepositoryImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public TroubleTicketManagemenDto consultarTicket(String id, String idType, String status, String authorizationToken) {
        try {
            String url = "http://localhost:8181/troubleTicketManagement/v5/troubleTicket/" + id +
                    "?idType=" + idType + "&status=" + status;

            HttpHeaders headers = new HttpHeaders();
            headers.add("Accept-Language", "<string>");
            headers.add("Accept", "application/json");
            headers.add("Authorization", "Bearer " + authorizationToken);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            logger.debug("Consultando ticket con URL: {}", url);
            ResponseEntity<TroubleTicketManagemenDto> response = restTemplate.exchange(url, HttpMethod.GET, entity, TroubleTicketManagemenDto.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            logger.error("Client error while consulting ticket with ID: {}", id, e);
            throw new RuntimeException(handleClientError(e));
        } catch (HttpServerErrorException e) {
            logger.error("Server error while consulting ticket with ID: {}", id, e);
            throw new RuntimeException(handleServerError(e));
        } catch (ResourceAccessException e) {
            logger.error("Timeout or connection error while consulting ticket with ID: {}", id, e);
            throw new RuntimeException(handleTimeoutError(id));
        } catch (Exception e) {
            logger.error("Unexpected error while consulting ticket with ID: {}", id, e);
            throw new RuntimeException(handleUnexpectedError(e, id));
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

    private String handleTimeoutError(String id) {
        ErrorDto errorResponse = new ErrorDto();
        errorResponse.setCode("408");
        errorResponse.setReason("Request Timeout");
        errorResponse.setMessage("Timeout or connection error occurred...");
        errorResponse.setStatus("REQUEST_TIMEOUT");
        errorResponse.setReferenceError(id);
        logger.error("Timeout error response: {}", errorResponse);
        return errorResponse.getMessage();
    }

    private String handleUnexpectedError(Exception e, String id) {
        ErrorDto errorResponse = new ErrorDto();
        errorResponse.setCode("500");
        errorResponse.setReason("Internal Server Error");
        errorResponse.setMessage("An unexpected error occurred.");
        errorResponse.setStatus("INTERNAL_SERVER_ERROR");
        errorResponse.setReferenceError(id);
        logger.error("Unexpected error response: {}", errorResponse);
        return errorResponse.getMessage();
    }
}