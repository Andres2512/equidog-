package com.equidog.repositories.city.facade;

import com.equidog.core.exceptions.base.ServiceException;
import com.equidog.domain.entity.CityEntity;
import com.equidog.domain.entity.CityEntity;

import java.util.List;

public interface CityRepositoryFacade {
    List<CityEntity> getAllCity() throws ServiceException;

    CityEntity getCityById(Long id) throws ServiceException;

    CityEntity createCity(CityEntity entity) throws ServiceException;

    //void deleteCompanyById(Long id) throws ServiceException;

}
