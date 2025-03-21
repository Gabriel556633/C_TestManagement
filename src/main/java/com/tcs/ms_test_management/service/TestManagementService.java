package com.tcs.ms_test_management.service;

import java.util.Optional;

public interface TestManagementService {
    Optional<Object> getClienteByNumeroServicio(String serviceNumber, String authorizationToken,String resourceSpecification,String channel);
}