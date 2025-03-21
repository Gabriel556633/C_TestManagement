package com.tcs.ms_test_management.service.impl;

import com.tcs.ms_test_management.domain.dto.*;
import com.tcs.ms_test_management.service.CreateTicketService;
import com.tcs.ms_test_management.service.RptTicketRemedyService;
import com.tcs.ms_test_management.service.TroubleTicketProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TroubleTicketProcessingServiceImpl implements TroubleTicketProcessingService {
    private static final Logger logger = LoggerFactory.getLogger(TroubleTicketProcessingServiceImpl.class);
    private final RptTicketRemedyService rptTicketRemedyService;

    @Autowired
    public TroubleTicketProcessingServiceImpl(RptTicketRemedyService rptTicketRemedyService ) {
        this.rptTicketRemedyService = rptTicketRemedyService;
    }


    @Override
    public ResponseDto processTickets(TroubleTicketManagemenDto troubleTicketManagemenDto , String authorizationToken) {
        try {
            for (var ticket : troubleTicketManagemenDto.getData()) {
                logger.info("PROCESSING TICKET: {}", ticket);

                if ("!Closed".equalsIgnoreCase(ticket.getStatus())) {
                    String categorizacionTier1 = null;
                    String categorizacionTier2 = null;
                    String categorizacionTier3 = null;

                    for (var characteristic : ticket.getTroubleTicketCharacteristic()) {
                        switch (characteristic.getName()) {
                            case "Categorization Tier 1":
                                categorizacionTier1 = characteristic.getValue();
                                break;
                            case "Categorization Tier 2":
                                categorizacionTier2 = characteristic.getValue();
                                break;
                            case "Categorization Tier 3":
                                categorizacionTier3 = characteristic.getValue();
                                break;
                        }
                    }

                    logger.info("CATEGORIZACION: {} {} {}", categorizacionTier1, categorizacionTier2, categorizacionTier3);

                    if (categorizacionTier1 != null && categorizacionTier2 != null && categorizacionTier3 != null) {
                        List<RptTicketRemedyDTO> remedies = rptTicketRemedyService.findByCategorization(categorizacionTier1, categorizacionTier2, categorizacionTier3);

                        if (!remedies.isEmpty()) {
                            // Crear ResponseDto con la información del primer remedy encontrado
                            RptTicketRemedyDTO remedy = remedies.get(0);
                            ResponseDto response = new ResponseDto();
                            response.setIdTicket("ID1: "+ticket.getId());
                            response.setDescription("Ticket existente no se necesita crear otro Ticket, Descripción: "+ticket.getDescription());
                            response.setEndDateTime(LocalDate.now());
                            response.setMode(remedy.getTecnologia());
                            response.setState(ticket.getDescription());
                            logger.info("RESPUESTA YA EXISTE UN TICKET NO SE NECESARIO CREAR OTRO TICKET");
                            return response;
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error al procesar los tickets: ", e);
        }

        return new ResponseDto(); // Si no se encontraron tickets que cumplan con los criterios
    }
}