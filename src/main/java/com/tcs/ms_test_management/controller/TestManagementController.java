package com.tcs.ms_test_management.controller;

import com.tcs.ms_test_management.domain.dto.ErrorDto;
import com.tcs.ms_test_management.domain.dto.ResponseDto;
import com.tcs.ms_test_management.service.TestManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tmf-api/serviceTestManagement/v4")
public class TestManagementController {

    private static final Logger logger = LoggerFactory.getLogger(TestManagementController.class);

    @Autowired
    private TestManagementService testManagementService;

    @GetMapping("/serviceTest/{serviceNumber}")
    public ResponseEntity<?> retrieveServiceTest(
            @PathVariable("serviceNumber") String serviceNumber,
            @RequestParam("resourceSpecification") String resourceSpecification,
            @RequestParam("channel") String channel,
            @RequestHeader("Authorization") String authorizationToken) {

        try {
            Optional<Object> response = testManagementService.getClienteByNumeroServicio(serviceNumber, authorizationToken, resourceSpecification, channel);
            return buildResponseEntity(response, serviceNumber);
        } catch (RuntimeException e) {
            logger.error("Error while retrieving service test with service number: {}", serviceNumber, e);
            ErrorDto error = new ErrorDto();;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    private ResponseEntity<?> buildResponseEntity(Optional<Object> response, String serviceNumber) {
        if (response.isPresent()) {
            Object responseBody = response.get();
            if (responseBody instanceof ResponseDto) {
                return ResponseEntity.ok(responseBody);
            } else if (responseBody instanceof ErrorDto) {
                ErrorDto error = (ErrorDto) responseBody;
                return ResponseEntity.status(HttpStatus.valueOf(Integer.parseInt(error.getCode()))).body(error);
            }
        }

        // Manejo de caso donde no hay respuesta (esto no debería ocurrir)
        ErrorDto error = createUnexpectedError();
        error.setReferenceError("serviceNumber: " + serviceNumber);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    private ErrorDto createUnexpectedError() {
        ErrorDto error = new ErrorDto();
        error.setCode("500");
        error.setReason("Unexpected error");
        error.setMessage("Ocurrió un error inesperado.");
        error.setStatus("INTERNAL_SERVER_ERROR");
        error.setReferenceError("");
        return error;
    }
}