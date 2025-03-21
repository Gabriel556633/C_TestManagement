package com.tcs.ms_test_management.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TroubleTicketManagemenDto {
    private int code;
    private String status;
    private String transactionId;
    private String timestamp;
    private List<TicketDataDTO> data;

    // Getters y Setters
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TicketDataDTO {
        private String id;
        private String description;
        private String status;
        private List<TroubleTicketCharacteristicDTO> troubleTicketCharacteristic;
        private List<RelatedEntityDTO> relatedEntity;
        private String href;

        // Getters y Setters
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TroubleTicketCharacteristicDTO {
        private String name;
        private String value;

        // Getters y Setters
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RelatedEntityDTO {
        private String role;
        private EntityDTO entity;

        // Getters y Setters
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class EntityDTO {
            private String name;

            // Getters y Setters
        }
    }
}
