package com.tcs.ms_test_management.service.impl;

import com.tcs.ms_test_management.domain.dto.RptTicketRemedyDTO;
import com.tcs.ms_test_management.domain.mapper.RptTicketRemedyMapper;
import com.tcs.ms_test_management.repository.RptTicketRemedyRepository;
import com.tcs.ms_test_management.service.RptTicketRemedyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RptTicketRemedyServiceImpl implements RptTicketRemedyService {

    private static final Logger logger = LoggerFactory.getLogger(RptTicketRemedyServiceImpl.class);

    private final RptTicketRemedyRepository rptTicketRemedyRepository;
    private final RptTicketRemedyMapper rptTicketRemedyMapper;

    @Autowired
    public RptTicketRemedyServiceImpl(RptTicketRemedyRepository rptTicketRemedyRepository,
                                      RptTicketRemedyMapper rptTicketRemedyMapper) {
        this.rptTicketRemedyRepository = rptTicketRemedyRepository;
        this.rptTicketRemedyMapper = rptTicketRemedyMapper;
    }

    @Override
    public List<RptTicketRemedyDTO> findByCategorization(String categorizacionTier1, String categorizacionTier2, String categorizacionTier3) {
        try {
            logger.info("Buscando tickets con categorización: Tier1={}, Tier2={}, Tier3={}", categorizacionTier1, categorizacionTier2, categorizacionTier3);
            List<RptTicketRemedyDTO> remedies = rptTicketRemedyRepository.findByCategorizacionTier1AndCategorizacionTier2AndCategorizacionTier3(categorizacionTier1, categorizacionTier2, categorizacionTier3).stream()
                    .map(rptTicketRemedyMapper::toDto)
                    .collect(Collectors.toList());
            logger.info("Se encontraron {} tickets con la categorización especificada.", remedies.size());
            return remedies;
        } catch (Exception e) {
            logger.error("Error al buscar tickets con categorización: Tier1={}, Tier2={}, Tier3={}", categorizacionTier1, categorizacionTier2, categorizacionTier3, e);
            throw e;
        }
    }

    @Override
    public List<RptTicketRemedyDTO> findByTecnologiaAndVaribleAValidar(String tecnologia, String varibleAValidar) {
        try {
            logger.info("Buscando tickets con tecnología: {} y variable_a_validar: {}", tecnologia, varibleAValidar);
            List<RptTicketRemedyDTO> remedies = rptTicketRemedyRepository.findByTecnologiaAndVaribleAValidar(tecnologia, varibleAValidar).stream()
                    .map(rptTicketRemedyMapper::toDto)
                    .collect(Collectors.toList());
            logger.info("Se encontraron {} tickets con la tecnología y variable_a_validar especificadas.", remedies.size());
            return remedies;
        } catch (Exception e) {
            logger.error("Error al buscar tickets con tecnología: {} y variable_a_validar: {}", tecnologia, varibleAValidar, e);
            throw e;
        }
    }

    @Override
    public List<RptTicketRemedyDTO> findByTecnologiaAndVaribleAValidarAndRango(String tecnologia, String varibleAValidar, Double rango) {
        try {
            logger.info("Buscando tickets con tecnología: {}, variable_a_validar: {} y rango: {}", tecnologia, varibleAValidar, rango);
            List<RptTicketRemedyDTO> remedies = rptTicketRemedyRepository.findByTecnologiaAndVaribleAValidarAndRango(tecnologia, varibleAValidar, rango).stream()
                    .map(rptTicketRemedyMapper::toDto)
                    .collect(Collectors.toList());
            logger.info("Se encontraron {} tickets con la tecnología, variable_a_validar y rango especificados.", remedies.size());
            return remedies;
        } catch (Exception e) {
            logger.error("Error al buscar tickets con tecnología: {}, variable_a_validar: {} y rango: {}", tecnologia, varibleAValidar, rango, e);
            throw e;
        }
    }
}