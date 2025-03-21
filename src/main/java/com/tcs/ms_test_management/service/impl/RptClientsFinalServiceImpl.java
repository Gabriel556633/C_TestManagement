package com.tcs.ms_test_management.service.impl;

import com.tcs.ms_test_management.domain.dto.RptClientsFinalDto;
import com.tcs.ms_test_management.domain.mapper.RptClientsFinalMapper;
import com.tcs.ms_test_management.repository.RptClientsFinalRepository;
import com.tcs.ms_test_management.service.RptClientsFinalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RptClientsFinalServiceImpl implements RptClientsFinalService {

    private static final Logger logger = LoggerFactory.getLogger(RptClientsFinalServiceImpl.class);

    private final RptClientsFinalRepository clientRepository;

    public RptClientsFinalServiceImpl(RptClientsFinalRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Optional<RptClientsFinalDto> getClienteByNumeroServicio(String serviceNumber) {
        try {
            logger.debug("Consultando cliente con número de servicio: {}", serviceNumber);
            return clientRepository.findByNumeroServicio(serviceNumber)
                    .map(cliente -> {
                        RptClientsFinalDto dto = RptClientsFinalMapper.INSTANCE.toDto(cliente);
                        logger.info("Cliente encontrado: {}", dto);
                        return dto;
                    });
        } catch (Exception e) {
            logger.error("Error al consultar el cliente con número de servicio: {}", serviceNumber, e);
            return Optional.empty();
        }
    }
}