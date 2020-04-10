package com.equidog.repositories.person.facade;

import com.equidog.core.exceptions.base.ServiceException;
import com.equidog.core.exceptions.enums.LogRefServices;
import com.equidog.core.exceptions.persistence.DataNotFoundPersistenceException;
import com.equidog.core.util.UserConverter;
import com.equidog.domain.dto.generic.UserPersonDTO;
import com.equidog.domain.entity.PersonEntity;
import com.equidog.repositories.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@Component
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class PersonRepositoryFacadeImpl implements PersonRepositoryFacade {


    private final PersonRepository personRepository;

    @Autowired
    public PersonRepositoryFacadeImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<PersonEntity> getAllPerson() throws ServiceException {
        try {
            return Optional.ofNullable(personRepository.findAll())
                    .orElseThrow(() -> new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "No se encontraron registros."));
        } catch (IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda del usuario.", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general.", e);
        }
    }

    @Override
    public PersonEntity getPersonById(Long id) throws ServiceException {
        try {
            return Optional.ofNullable(personRepository.findPersonEntityById(id))
                    .orElseThrow(() -> new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "No se encontro usuario por el Id"));
        } catch (IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda del usuario.", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general.", e);
        }
    }

    @Override
    public PersonEntity findPersonAllInfoByDocumentNumber(String documentNumber) throws ServiceException {
        try {
            return Optional.ofNullable(personRepository.findPersonAllInfoById(documentNumber))
                    .orElseThrow(() -> new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "No se encontro usuario por el Id"));
        } catch (DataNotFoundPersistenceException | IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda del usuario.", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general.", e);
        }
    }

    /*  @Override
      public UserEntity findByUsername(String username) throws ServiceException {
          return  userRepository.findByUsername(username);
      }
  */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserPersonDTO createPerson(UserPersonDTO entity) throws ServiceException {
        return UserConverter.toPersonDTO(executeSaver(UserConverter.convertToPerson(entity)));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PersonEntity createPerson(PersonEntity entity) throws ServiceException {
        return executeSaver(entity);
    }

    private PersonEntity executeSaver(PersonEntity entity) throws ServiceException {
        try {
            return personRepository.save(entity);
        } catch (IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda del usuario.", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general.", e);
        }
    }


    @Override
    public void deletePersonByDocumentNumber(String documentNumber) throws ServiceException {
        PersonEntity personEntity = Optional.ofNullable(personRepository.findPersonAllInfoById(documentNumber))
                .orElseThrow(() -> new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_CORRUPTO, "El id del usuario no existe"));
        personRepository.delete(personEntity);
    }


}
