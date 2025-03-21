package com.tcs.ms_test_management.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignalValidationErrorDto {
    private String code;
    private String status;
    private String transactionId;
    private ZonedDateTime timestamp;
    private String message;
    private ErrorDetails errorDetails;

    // Getters and Setters
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ErrorDetails {
        private String errorCode;
        private String message;
        private String action;

        // Getters and Setters
    }
}
