package com.equidog.repositories.company.facade;

import com.equidog.core.exceptions.base.ServiceException;
import com.equidog.domain.dto.generic.CompanyDTO;
import com.equidog.domain.entity.CompanyEntity;

import java.util.List;

public interface CompanyRepositoryFacade {

    List<CompanyEntity> getAllCompany() throws ServiceException;

    CompanyDTO getCompanyDtoById(Long id) throws ServiceException;

    CompanyEntity getCompanyById(Long id) throws ServiceException;

    CompanyDTO createCompany(CompanyDTO entity) throws ServiceException;

    CompanyDTO updateStatus(Boolean isActive, long id) throws ServiceException;

    CompanyDTO updateCompany(CompanyDTO companyDTO) throws ServiceException;
}
