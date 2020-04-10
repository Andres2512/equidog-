package com.equidog.repositories.company.facade;

import com.equidog.core.exceptions.base.ServiceException;
import com.equidog.core.exceptions.enums.LogRefServices;
import com.equidog.core.exceptions.persistence.DataNotFoundPersistenceException;
import com.equidog.core.exceptions.service.DataNotFoundServiceException;
import com.equidog.core.util.CompanyConverter;
import com.equidog.domain.dto.generic.CompanyDTO;
import com.equidog.domain.entity.CompanyEntity;
import com.equidog.repositories.company.CompanyRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@Component
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class CompanyRepositoryFacadeImpl implements CompanyRepositoryFacade {

    private final CompanyRepository companyRepository;
    private final CompanyConverter personConverter;
    public CompanyRepositoryFacadeImpl(CompanyRepository companyRepository, CompanyConverter personConverter) {
        this.companyRepository = companyRepository;
        this.personConverter = personConverter;
    }


    @Override
    public List<CompanyEntity> getAllCompany() throws ServiceException {
        try {
            return Optional.ofNullable(companyRepository.findAll())
                    .orElseThrow(() -> new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "No se encontraron registros"));
        } catch (IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de ciudad", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "error general", e);
        }
    }

    @Override
    public CompanyDTO getCompanyDtoById(Long id) throws ServiceException {
        return CompanyConverter.convertToCompanyDTO(executeFinder(id));

    }


    @Override
    public CompanyEntity getCompanyById(Long id) throws ServiceException {
        return executeFinder(id);
    }
    private CompanyEntity executeFinder(Long id) throws ServiceException {
        try {
            return Optional.ofNullable(companyRepository.findCompanyEntitiesById(id)).filter(CompanyEntity::getIsActive)
                    .orElseThrow(() -> new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "No se encontro usuario por el Id"));
        } catch (IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda del usuario.", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general.", e);
        }
    }

    @Override
    public CompanyDTO createCompany(CompanyDTO entity) throws ServiceException {
        try {
            return CompanyConverter.convertToCompanyDTO(companyRepository.save(personConverter.convertToCompany(entity)));

        } catch (IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda del usuario.", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general.", e);
        }
    }

    @Override
    public CompanyDTO updateStatus(Boolean isActive, long id) throws ServiceException {
        try {
            return CompanyConverter.convertToCompanyDTO(companyRepository.save(personConverter.convertToCompany(mapperCompanyUpdateStatus(isActive, id))));
        } catch (IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda del usuario.", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general.", e);
        }
    }

    @Override
    public CompanyDTO updateCompany(CompanyDTO companyDTO) throws ServiceException {
        try {
           return CompanyConverter.convertToCompanyDTO(companyRepository.save(personConverter.convertToCompany(mapperCompanyUpdate(companyDTO))));
        } catch (IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda del usuario.", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general.", e);
        }
    }

    private CompanyDTO mapperCompanyUpdateStatus(Boolean isActive, long id) throws ServiceException {
        CompanyDTO companyActual = getCompanyDtoById(id);
        companyActual.setIsActive(isActive);
        return companyActual;
    }

    private CompanyDTO mapperCompanyUpdate (CompanyDTO companyDTO) throws ServiceException{
        CompanyDTO companyActual = getCompanyDtoById(companyDTO.getId());
        companyActual.setIsActive(companyDTO.getIsActive());
        companyActual.setAddress(companyDTO.getAddress());
        companyActual.setName(companyActual.getName());
        companyActual.setPhone(companyDTO.getPhone());
        return companyActual;
    }
}
