package com.tcs.ms_test_management.service;

import com.tcs.ms_test_management.domain.dto.RptTicketRemedyDTO;

import java.util.List;

public interface RptTicketRemedyService {
    List<RptTicketRemedyDTO> findByCategorization(String categorizacionTier1, String categorizacionTier2, String categorizacionTier3);
    List<RptTicketRemedyDTO> findByTecnologiaAndVaribleAValidar(String tecnologia, String varibleAValidar);
    List<RptTicketRemedyDTO> findByTecnologiaAndVaribleAValidarAndRango(String tecnologia, String varibleAValidar, Double rango); // Nuevo método
}