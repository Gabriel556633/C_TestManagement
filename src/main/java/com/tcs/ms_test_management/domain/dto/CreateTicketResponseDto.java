package com.tcs.ms_test_management.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketResponseDto {
    private String code;
    private String status;
    private String message;
    private String transactionId;
    private String timestamp;
    private DataDTO data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataDTO {
        private String id;
    }
}
