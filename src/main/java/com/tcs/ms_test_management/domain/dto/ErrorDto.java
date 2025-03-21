package com.tcs.ms_test_management.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {
    private String code;
    private String reason;
    private String message;
    private String status;
    private String referenceError;

    // Getters and Setters
}
