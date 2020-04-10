package com.equidog.repositories.person.facade;

import com.equidog.core.exceptions.base.ServiceException;
import com.equidog.domain.dto.generic.UserPersonDTO;
import com.equidog.domain.entity.PersonEntity;

import java.util.List;

public interface PersonRepositoryFacade {

    List<PersonEntity> getAllPerson() throws ServiceException;

    PersonEntity getPersonById(Long id) throws ServiceException;

    public PersonEntity findPersonAllInfoByDocumentNumber(String documentNumber) throws ServiceException;

    UserPersonDTO createPerson(UserPersonDTO entity) throws ServiceException;

    PersonEntity createPerson(PersonEntity entity) throws ServiceException;

    void deletePersonByDocumentNumber(String documentNumber) throws ServiceException;
}
