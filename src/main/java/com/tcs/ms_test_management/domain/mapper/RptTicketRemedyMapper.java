package com.tcs.ms_test_management.domain.mapper;

import com.tcs.ms_test_management.domain.dto.RptClientsFinalDto;
import com.tcs.ms_test_management.domain.dto.RptTicketRemedyDTO;
import com.tcs.ms_test_management.domain.model.RptReplicaClientesFinal;
import com.tcs.ms_test_management.domain.model.RptTicketRemedy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RptTicketRemedyMapper {
    RptTicketRemedyMapper INSTANCE = Mappers.getMapper(RptTicketRemedyMapper.class);

    RptTicketRemedyDTO toDto(RptTicketRemedy entity);
    RptTicketRemedy toEntity(RptTicketRemedyDTO dto);
}