package com.equidog.services.company.impl;

import com.equidog.core.exceptions.base.ServiceException;
import com.equidog.domain.dto.generic.CompanyDTO;
import com.equidog.domain.entity.CompanyEntity;
import com.equidog.repositories.company.facade.CompanyRepositoryFacade;
import com.equidog.services.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class CompanyServiceImpl implements CompanyService {

    private final CompanyRepositoryFacade companyRepositoryFacade;

    CompanyServiceImpl(CompanyRepositoryFacade companyRepositoryFacade) {
        this.companyRepositoryFacade = companyRepositoryFacade;
    }


    @Override
    public List<CompanyDTO> getAllCompany() throws ServiceException {

        List<CompanyEntity> personList = companyRepositoryFacade.getAllCompany();
        return personList.stream().map(this::mapperUserEntity).collect(Collectors.toList());
    }

    private CompanyDTO mapperUserEntity(CompanyEntity entity) {
        return CompanyDTO.builder().name(entity.getName()).phone(entity.getPhone()).address(entity.getAddress()).build();
    }

    @Override
    public CompanyDTO getCompanyById(Long id) throws ServiceException {
        return companyRepositoryFacade.getCompanyDtoById(id);
    }


    @Override
    public CompanyDTO createCompany(CompanyDTO companyDTO) throws ServiceException {
       return companyRepositoryFacade.createCompany(companyDTO);

    }


    @Override
    public CompanyDTO updateStatusCompany(Boolean isActive, long id) throws ServiceException {
        return companyRepositoryFacade.updateStatus(isActive, id);
    }

    @Override
    public CompanyDTO updateCompany(CompanyDTO companyDTO) throws ServiceException {
        return companyRepositoryFacade.updateCompany(companyDTO);
    }


}
