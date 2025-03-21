package com.tcs.ms_test_management.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "RPT_TIPOLOGIA_TICKET_REMEDY")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RptTicketRemedy {

    @Id
    @Column(name = "TECNOLOGIA", nullable = false, length = 10)
    private String tecnologia;

    @Column(name = "TIPOAFECTACION", nullable = false, length = 100)
    private String tipoAfectacion;

    @Column(name = "CATEGORIZACIONTIER_1", nullable = false, length = 500)
    private String categorizacionTier1;

    @Column(name = "CATEGORIZACIONTIER_2", nullable = false, length = 500)
    private String categorizacionTier2;

    @Column(name = "CATEGORIZACIONTIER_3", nullable = false, length = 500)
    private String categorizacionTier3;

    @Column(name = "GRUPO_ASIGNADO", nullable = false, length = 500)
    private String grupoAsignado;

    @Column(name = "GRUPO_SOPORTE_DESTINO", nullable = false, length = 500)
    private String grupoSoporteDestino;

    @Column(name = "MOTIVO", nullable = false, length = 500)
    private String motivo;

    @Column(name = "RANGO_INICIAL", nullable = false, length = 500)
    private int rangoInicial;

    @Column(name = "RANGO_FINAL", nullable = false, length = 500)
    private int rangoFinal;

    @Column(name = "FORMULA", nullable = false, length = 500)
    private String formula;

    @Column(name = "ESTADO", nullable = false, length = 1)
    private String estado;

    @Column(name = "FECHA_CREACION", nullable = false)
    private Date fechaCreacion;

    @Column(name = "FECHA_ACTUALIZACION", nullable = false)
    private Date fechaActualizacion;

    @Column(name = "VARIBLE_A_VALIDAR", nullable = false, length = 500)
    private String varibleAValidar;
}
