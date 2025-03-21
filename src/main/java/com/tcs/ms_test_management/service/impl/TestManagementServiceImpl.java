package com.tcs.ms_test_management.service.impl;

import com.tcs.ms_test_management.domain.dto.*;
import com.tcs.ms_test_management.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestManagementServiceImpl implements TestManagementService {

    private static final Logger logger = LoggerFactory.getLogger(TestManagementServiceImpl.class);

    private final RptClientsFinalService rptClientsFinalService;
    private final TroubleTicketManagementService troubleTicketManagementService;
    private final TroubleTicketProcessingService troubleTicketProcessingService;
    private final SignalValidationService signalValidationService;
    private final SignalValidationProcessingService signalValidationProcessingService;

    public TestManagementServiceImpl(RptClientsFinalService rptClientsFinalService,
                                     TroubleTicketManagementService troubleTicketManagementService,
                                     TroubleTicketProcessingService troubleTicketProcessingService,
                                     SignalValidationService signalValidationService, SignalValidationProcessingService signalValidationProcessingService) {
        this.rptClientsFinalService = rptClientsFinalService;
        this.troubleTicketManagementService = troubleTicketManagementService;
        this.troubleTicketProcessingService = troubleTicketProcessingService;
        this.signalValidationService = signalValidationService;
        this.signalValidationProcessingService = signalValidationProcessingService;
    }

    @Override
    public Optional<Object> getClienteByNumeroServicio(String serviceNumber, String authorizationToken,
                                                       String resourceSpecification, String channel) {
        try {
            return rptClientsFinalService.getClienteByNumeroServicio(serviceNumber)
                    .map(cliente -> procesarCliente(serviceNumber, authorizationToken, resourceSpecification, cliente))
                    .orElseGet(() -> generarRespuestaNoClienteEncontrado(serviceNumber,resourceSpecification));
        } catch (Exception e) {
            logger.error("Error inesperado al procesar el cliente: {}", serviceNumber, e);
            ErrorDto error = new ErrorDto();
            error.setCode("500");
            error.setReason("Internal Server Error");
            error.setMessage("An unexpected error occurred.");
            error.setStatus("INTERNAL_SERVER_ERROR");
            error.setReferenceError(serviceNumber);
            return Optional.of(error);
        }
    }

    private Optional<Object> procesarCliente(String serviceNumber, String authorizationToken,
                                             String resourceSpecification, RptClientsFinalDto clienteDto) {
        try {
            TroubleTicketManagemenDto ticket = troubleTicketManagementService.consultarTicket(
                    clienteDto.getIdentificacionCliente(),
                    mapDocumentType(clienteDto.getTipoIdentificacion()),
                    "Assigned",
                    authorizationToken
            );

            if (ticket != null && !ticket.getData().isEmpty()) {
                logger.info("Ticket encontrado: " + ticket);
                ResponseDto response = troubleTicketProcessingService.processTickets(ticket,authorizationToken);
                if(response.getIdTicket().isEmpty()){
                    logger.info("SEGUIR CON SERVICIO VALIDACION");
                    Optional<SignalValidationSuccessDto> susccessValidationDto = signalValidationService.getSignalValidation(serviceNumber,resourceSpecification,authorizationToken);
                    logger.info("Datos validacion: "+susccessValidationDto);
                    if (susccessValidationDto.isPresent()) {
                        ResponseDto validationResponse = signalValidationProcessingService.processSignalValidation(susccessValidationDto.get(),authorizationToken);
                        logger.info("RESPUESTA PROCESING VALIDACION CREAR UN TICKET: ");
                        return Optional.of(validationResponse);
                    } else {
                        System.out.println("5555555555555555555555555555555");
                        logger.warn("No se encontró validación de señal para el cliente: {}", clienteDto.getIdentificacionCliente());
                        return Optional.empty();
                    }
                }logger.info("Ticket ya existe no se necesita crear otro ticket");
                return Optional.of(response);
            } else {
                logger.info("No se encontraron tickets para el cliente: {}", clienteDto.getIdentificacionCliente());
                return Optional.empty();
            }
        } catch (Exception e) {

            logger.error("Error al consultar el ticket: {}", clienteDto.getIdentificacionCliente(), e);
            ErrorDto error = new ErrorDto();
            error.setCode("408");
            error.setReason("Internal Server Error");
            error.setMessage(e.getMessage());
            error.setStatus(e.getMessage());
            error.setReferenceError(clienteDto.getIdentificacionCliente());
            return Optional.of(error);
        }
    }

    private Optional<Object> generarRespuestaNoClienteEncontrado(String serviceNumber,String resourceSpecification) {
        ResponseDto response = new ResponseDto();
        response.setIdTicket("");
        response.setDescription("Cliente no encontrado con el número de servicio proporcionado:"+serviceNumber);
        response.setMode(resourceSpecification);
        response.setState("SERVICE_NOT_FOUND");
        logger.warn("Cliente no encontrado: {}", serviceNumber);
        return Optional.of(response);
    }

    private String mapDocumentType(String tipoIdentificacion) {
        return switch (tipoIdentificacion) {
            case "Cédula", "Cedula" -> "CED";
            case "Pasaporte" -> "PAS";
            default -> "";
        };
    }

    private ResponseDto mapToResponseDto(RptClientsFinalDto cliente, TroubleTicketManagemenDto ticket) {
        ResponseDto response = new ResponseDto();
        response.setIdTicket("");
        // Mapear más campos si es necesario
        return response;
    }
}