package com.tcs.ms_test_management.service.impl;

import com.tcs.ms_test_management.domain.dto.*;
import com.tcs.ms_test_management.service.CreatorTicketsService;
import com.tcs.ms_test_management.service.RptTicketRemedyService;
import com.tcs.ms_test_management.service.SignalValidationProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SignalValidationProcessingServiceImpl implements SignalValidationProcessingService {

    private static final Logger logger = LoggerFactory.getLogger(SignalValidationProcessingServiceImpl.class);

    private final RptTicketRemedyService rptTicketRemedyService;
    private final CreatorTicketsService creatorTicketsService;

    @Autowired
    public SignalValidationProcessingServiceImpl(RptTicketRemedyService rptTicketRemedyService, CreatorTicketsService creatorTicketsService) {
        this.rptTicketRemedyService = rptTicketRemedyService;
        this.creatorTicketsService = creatorTicketsService;
    }

    @Override
    public ResponseDto processSignalValidation(SignalValidationSuccessDto signalValidationSuccessDto,
                                               String authorizationToken) {
        try {
            System.out.println("METRIC DESCRIPTION: "+ signalValidationSuccessDto.getTestMeasure().size());
            for (SignalValidationSuccessDto.TestMeasure testMeasure : signalValidationSuccessDto.getTestMeasure()) {

                if ("!OK".equalsIgnoreCase(testMeasure.getMetricDescription())){
                    List<RptTicketRemedyDTO> remedies = rptTicketRemedyService.findByTecnologiaAndVaribleAValidarAndRango(
                                                                                signalValidationSuccessDto.getMode(),
                                                                                testMeasure.getMetricName(),
                                                                                Double.parseDouble(testMeasure.getValue().getValue()));

                    if(remedies.isEmpty()){
                        ResponseDto response = new ResponseDto();
                        response.setIdTicket("");
                        response.setDescription("Error Remedy es nula");
                        response.setEndDateTime(LocalDate.now());
                        response.setMode(signalValidationSuccessDto.getMode());
                        response.setState("ERRO DATOS");
                        logger.info("ERROR BASE REMEDY DEVUELVE VACIO");
                        return response;
                    }
                    CreateTicketResponseDto createTicketResponseDto = creatorTicketsService.creatorTicket(
                            remedies,
                            authorizationToken);
                        // Crear ResponseDto con la información del primer remedy encontrado

                        ResponseDto response = new ResponseDto();
                        response.setIdTicket(createTicketResponseDto.getData().getId());
                        response.setDescription("SE CREO UN TICKET PARA EL ERROR: "+remedies.get(0).getMotivo());
                        response.setEndDateTime(LocalDate.now());
                        response.setMode(signalValidationSuccessDto.getMode());
                        response.setState(createTicketResponseDto.getStatus());
                        logger.info("RESPUESTA DEL SIGNAL VALIDATION y REMEDY: {}", response);
                        return response;
                }
            }
            //
            List<RptTicketRemedyDTO> remedies = rptTicketRemedyService.findByTecnologiaAndVaribleAValidar(signalValidationSuccessDto.getMode(),"OK");
            CreateTicketResponseDto createTicketResponseDto = creatorTicketsService.creatorTicket(
                    remedies,
                    authorizationToken);

            ResponseDto response = new ResponseDto();
            response.setIdTicket(createTicketResponseDto.getData().getId());
            response.setDescription("Se creo un TICKET DE SEGUIMIENTO");
            response.setEndDateTime(LocalDate.now());
            response.setMode(signalValidationSuccessDto.getMode());
            response.setState(createTicketResponseDto.getStatus());
            logger.info("GENERAR TICKET SEGUIMIENTO");
            return response;
        } catch (Exception e) {
            logger.error("Error al procesar la validación de señal: ", e);
            // Manejar el error y devolver un ResponseDto vacío o con información del error
            ResponseDto response = new ResponseDto();
            response.setIdTicket(signalValidationSuccessDto.getId());
            response.setDescription("Error al procesar la validación de señal");
            response.setEndDateTime(LocalDate.now());
            return response;
        }// Si no se encontraron resultados que cumplan con los criterios
    }
}