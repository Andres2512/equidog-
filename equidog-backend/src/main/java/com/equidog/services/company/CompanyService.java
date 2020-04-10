package com.equidog.services.company;

import com.equidog.core.exceptions.base.ServiceException;
import com.equidog.domain.dto.generic.CompanyDTO;
import com.equidog.domain.dto.request.UserRequestDTO;
import com.equidog.domain.dto.response.UserResponseDTO;

import java.util.List;

public interface CompanyService {

    List<CompanyDTO> getAllCompany() throws ServiceException;

    CompanyDTO getCompanyById(Long id) throws ServiceException;

    CompanyDTO createCompany(CompanyDTO companyDTO) throws ServiceException;

    CompanyDTO updateStatusCompany(Boolean isActive, long id) throws ServiceException;

    CompanyDTO updateCompany(CompanyDTO companyDTO) throws ServiceException;

   // void deleteCompany(Long id) throws ServiceException;
}
