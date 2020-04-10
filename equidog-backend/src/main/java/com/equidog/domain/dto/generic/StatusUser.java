package com.equidog.domain.dto.generic;

import com.equidog.core.exceptions.enums.LogRefServices;
import com.equidog.core.exceptions.service.DataCorruptedServiceException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.stream.Stream;


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum StatusUser {

    ACTIVE(0), INACTIVE(1);


    private final int code;

    @JsonValue
    public int getCode() {
        return code;
    }

    public String fromCode(int code) throws DataCorruptedServiceException {
        return Stream.of(StatusUser.values()).filter(m -> m.getCode() == code).findFirst().map(StatusUser::name)
                .orElseThrow(() -> new DataCorruptedServiceException(LogRefServices.ERROR_DATO_CORRUPTO, "Error al buscar estado por código."));
    }


    public String fromName(String name) throws DataCorruptedServiceException {
        return Stream.of(StatusUser.values()).filter(m -> m.name().equals(name)).findFirst().map(StatusUser::name)
                .orElseThrow(() -> new DataCorruptedServiceException(LogRefServices.ERROR_DATO_CORRUPTO, "Error al buscar estado por nombre."));
    }
}
