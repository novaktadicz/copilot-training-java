package com.zuehlke.telemetry.processor.mapper;

import com.zuehlke.telemetry.processor.model.RawTelemetryDTO;
import com.zuehlke.telemetry.processor.model.TelemetryData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TelemetryMapper {

    TelemetryMapper INSTANCE = Mappers.getMapper(TelemetryMapper.class);

    @Mapping(target = "processedAt", expression = "java(java.time.Instant.now())")
    TelemetryData map(RawTelemetryDTO dto);
}
