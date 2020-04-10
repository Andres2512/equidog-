package com.equidog.repositories.city.facade;

import com.equidog.core.exceptions.base.ServiceException;
import com.equidog.core.exceptions.enums.LogRefServices;
import com.equidog.core.exceptions.persistence.DataNotFoundPersistenceException;
import com.equidog.core.exceptions.service.DataNotFoundServiceException;
import com.equidog.domain.entity.CityEntity;
import com.equidog.repositories.city.CityRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@Component
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class CityRepositoryFacadeImpl implements CityRepositoryFacade {

    private final CityRepository cityRepository;

    public CityRepositoryFacadeImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<CityEntity> getAllCity() throws ServiceException {
        try{
            return Optional.ofNullable(cityRepository.findAll())
                    .orElseThrow(() -> new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "No se encontraron registros."));
        } catch (IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de la ciudad.", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general.", e);
        }
    }

    @Override
    public CityEntity getCityById(Long id) throws ServiceException {
        try {
            return Optional.ofNullable(cityRepository.findCityEntityById(id))
                    .orElseThrow(() -> new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "No se encontro usuario por el Id"));
        } catch (IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda del usuario.", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general.", e);
        }
    }

    @Override
    public CityEntity createCity(CityEntity entity) throws ServiceException {
            try {
                return cityRepository.save(entity);

            } catch (IllegalArgumentException | PersistenceException pe) {
                throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda del usuario.", pe);
            } catch (Exception e) {
                throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general.", e);
            }
    }
}
