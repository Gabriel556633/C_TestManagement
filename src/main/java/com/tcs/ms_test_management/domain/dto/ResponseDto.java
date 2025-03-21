package com.tcs.ms_test_management.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private String description="";
    private String idTicket="";
    private LocalDate endDateTime=LocalDate.now();
    private String state="";
    private String mode="";
    private List<TestMeasure> testMeasure=new ArrayList<>();

    // Getters and Setters
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Characteristic {
        private String id;
        private String name;
        private String valueType;
        private List<CharacteristicRelationship> characteristicRelationship;
        private String value;

        // Getters and Setters
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class CharacteristicRelationship {
            private String id;
            private String href;
            private String relationshipType;

            // Getters and Setters
        }
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RelatedService {
        private String id;
        private String name;
        private String value;

        // Getters and Setters
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TestMeasure {
        private int accuracy;
        private ZonedDateTime captureDateTime;
        private String captureMethod;
        private String metricDescription;
        private String metricHref;
        private String metricName;
        private String unitOfMeasure;
        private List<RuleViolation> ruleViolation;
        private Value value;

        // Getters and Setters
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class RuleViolation {
            private boolean conformanceComparatorExact;
            private String conformanceComparatorLower;
            private String conformanceComparatorUpper;
            private String conformanceTargetExact;
            private String conformanceTargetLower;
            private String conformanceTargetUpper;
            private String description;
            private String name;
            private int numberOfAllowedCrossing;
            private String thresholdRuleSeverity;
            private List<AppliedConsequence> appliedConsequence;
            private TolerancePeriod tolerancePeriod;

            // Getters and Setters
            @Data
            @NoArgsConstructor
            @AllArgsConstructor
            public static class AppliedConsequence {
                private String appliedAction;
                private String description;
                private String name;
                private boolean repeatAction;

                // Getters and Setters
            }
            @Data
            @NoArgsConstructor
            @AllArgsConstructor
            public static class TolerancePeriod {
                private int amount;
                private String units;

                // Getters and Setters
            }
        }
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Value {
            private String id;
            private String name;
            private String valueType;
            private List<CharacteristicRelationship> characteristicRelationship;
            private String value;

            // Getters and Setters
            @Data
            @NoArgsConstructor
            @AllArgsConstructor
            public static class CharacteristicRelationship {
                private String id;
                private String href;
                private String relationshipType;

                // Getters and Setters
            }
        }
    }
}
