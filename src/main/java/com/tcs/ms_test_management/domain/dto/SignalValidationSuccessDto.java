package com.tcs.ms_test_management.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignalValidationSuccessDto {
    private String description;
    private String id;
    private LocalDateTime endDateTime;
    private String state;
    private String mode;
    private List<TestMeasure> testMeasure;
    private RelatedService relatedService;

    // Getters and Setters
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TestMeasure {
        private LocalDateTime captureDateTime;
        private String metricDescription;
        private String metricName;
        private String unitOfMeasure;
        private List<RuleViolation> ruleViolation;
        private Value value;

        // Getters and Setters
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class RuleViolation {
            private String conformanceComparatorLower;
            private String conformanceComparatorUpper;
            private String conformanceTargetLower;
            private String conformanceTargetUpper;

            // Getters and Setters
        }
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Value {
            private String id;
            private String name;
            private String valueType;
            private String value;

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
}