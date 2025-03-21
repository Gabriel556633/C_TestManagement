package com.tcs.ms_test_management.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RptTicketRemedyDTO {

    private String tecnologia;
    private String tipoAfectacion;
    private String categorizacionTier1;
    private String categorizacionTier2;
    private String categorizacionTier3;
    private String grupoAsignado;
    private String grupoSoporteDestino;
    private String motivo;
    private String rangoInicial;
    private String rangoFinal;
    private String formula;
    private String estado;
}
