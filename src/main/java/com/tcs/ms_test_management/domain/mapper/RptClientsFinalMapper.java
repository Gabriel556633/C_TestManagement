package com.tcs.ms_test_management.domain.mapper;

import com.tcs.ms_test_management.domain.dto.RptClientsFinalDto;
import com.tcs.ms_test_management.domain.model.RptReplicaClientesFinal;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RptClientsFinalMapper {
    RptClientsFinalMapper INSTANCE = Mappers.getMapper(RptClientsFinalMapper.class);

    RptClientsFinalDto toDto(RptReplicaClientesFinal entity);
    RptReplicaClientesFinal toEntity(RptClientsFinalDto dto);
}