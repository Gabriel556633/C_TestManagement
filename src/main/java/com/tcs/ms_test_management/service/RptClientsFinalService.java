package com.tcs.ms_test_management.service;

import com.tcs.ms_test_management.domain.dto.RptClientsFinalDto;

import java.util.Optional;

public interface RptClientsFinalService {
    Optional<RptClientsFinalDto> getClienteByNumeroServicio(String numeroServicio);
}
