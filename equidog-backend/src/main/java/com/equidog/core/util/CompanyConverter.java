package com.equidog.core.util;

import com.equidog.domain.dto.generic.CompanyDTO;
import com.equidog.domain.entity.CompanyEntity;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CompanyConverter {

    public List<CompanyDTO> convertToDTO(Collection<CompanyEntity> list) {
        return list.stream().map(entity -> convertToCompanyDTO(entity)).collect(Collectors.toList());
    }

    public List<CompanyEntity> convertToEntity(Collection<CompanyDTO> userDto) {
        return CollectionUtils.isEmpty(userDto) ? Collections.emptyList() : userDto.stream().filter(Objects::nonNull)
                .map(this::convertToCompany).collect(Collectors.toList());
    }

    public static CompanyDTO convertToCompanyDTO(CompanyEntity entity) {

        return CompanyDTO.builder()

                .id(entity.getId())
                .nit(entity.getNit())
                .isActive(entity.getIsActive())
                .name(entity.getName())
                .phone(entity.getPhone())
                .address(entity.getAddress()).build();
    }

    public CompanyEntity convertToCompany(CompanyDTO companyDTO) {


        CompanyEntity.CompanyEntityBuilder company = CompanyEntity.builder();
        if (ObjectUtils.isNotEmpty(companyDTO.getId()))
            company.id(companyDTO.getId());
        if (ObjectUtils.isNotEmpty(companyDTO.getIsActive())){
            company.isActive(companyDTO.getIsActive());
        }else {
            company.isActive(true);
        }
        company.name(companyDTO.getName())
                .nit(companyDTO.getNit())
                .address(companyDTO.getAddress())
                .phone(companyDTO.getPhone());
        return company.build();
    }
}
