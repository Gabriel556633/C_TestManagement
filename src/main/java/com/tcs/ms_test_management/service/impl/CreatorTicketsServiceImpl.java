package com.tcs.ms_test_management.service.impl;

import com.tcs.ms_test_management.domain.dto.*;
import com.tcs.ms_test_management.repository.CreateTicketRepository;
import com.tcs.ms_test_management.service.CreateTicketService;
import com.tcs.ms_test_management.service.CreatorTicketsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CreatorTicketsServiceImpl implements CreatorTicketsService {

    private static final Logger logger = LoggerFactory.getLogger(CreatorTicketsServiceImpl.class);

    private final CreateTicketService createTicketService;


    @Autowired
    public CreatorTicketsServiceImpl(CreateTicketService createTicketService) {
        this.createTicketService = createTicketService;
    }


    @Override
    public CreateTicketResponseDto creatorTicket(List<RptTicketRemedyDTO> remedies,
                                                 String authorizationToken) {
        try {
                // Crear un Ticket de seguimiento
                CreateTicketRequestDto createTicketRequest = new CreateTicketRequestDto();

                createTicketRequest.setTicketType("CREATE");
                createTicketRequest.setRelatedParty(new ArrayList<>());
                createTicketRequest.setDescription(remedies.get(0).getTipoAfectacion());
                createTicketRequest.setStatus(remedies.get(0).getEstado());
                createTicketRequest.setServiceType(remedies.get(0).getGrupoAsignado());
                createTicketRequest.setSeverity(remedies.get(0).getMotivo());
                createTicketRequest.setPriority("");
                createTicketRequest.setRelatedEntity(Collections.emptyList());
                CreateTicketRequestDto.TroubleTicketCharacteristicDTO characteristic = new CreateTicketRequestDto.TroubleTicketCharacteristicDTO();
                characteristic.setName("CategorizationTier1");
                characteristic.setValue(remedies.get(0).getCategorizacionTier1());
                characteristic.setName("CategorizationTier2");
                characteristic.setValue(remedies.get(0).getCategorizacionTier2());
                characteristic.setName("CategorizationTier3");
                characteristic.setValue(remedies.get(0).getCategorizacionTier3());
                createTicketRequest.getTroubleTicketCharacteristic().add(characteristic);
                CreateTicketResponseDto createTicketResponse = createTicketService.createTicket(createTicketRequest, authorizationToken);
                System.out.println("RESPUESTA CREACION TICKET: "+ createTicketResponse);
                createTicketService.createTicket(createTicketRequest, authorizationToken);
                return createTicketResponse;

        } catch (RuntimeException e) {
            logger.error("Error while creating ticket", e);
            throw e; // Propaga la excepción al controlador
        }
    }
}
