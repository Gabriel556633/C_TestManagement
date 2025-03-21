package com.tcs.ms_test_management.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketRequestDto {
    private String ticketType;
    private List<RelatedPartyDTO> relatedParty;
    private String description;
    private String descriptionDetails;
    private String status;
    private String serviceType;
    private String severity;
    private String priority;
    private List<RelatedEntityDTO> relatedEntity;
    private List<TroubleTicketCharacteristicDTO> troubleTicketCharacteristic=new ArrayList<>();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RelatedPartyDTO {
        private String role;
        private EntityRefDTO entityRef;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class EntityRefDTO {
            private String id;
            private String name;
            private String lastName;
            private String type;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RelatedEntityDTO {
        private String role;
        private EntityDTO entity;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class EntityDTO {
            private String name;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TroubleTicketCharacteristicDTO {
        private String name;
        private String value;
    }
}
