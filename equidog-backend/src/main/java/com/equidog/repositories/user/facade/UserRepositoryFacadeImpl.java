package com.equidog.repositories.user.facade;

import com.equidog.core.exceptions.base.ServiceException;
import com.equidog.core.exceptions.enums.LogRefServices;
import com.equidog.core.exceptions.persistence.DataNotFoundPersistenceException;
import com.equidog.core.exceptions.service.DataNotFoundServiceException;
import com.equidog.core.util.UserConverter;
import com.equidog.domain.dto.generic.UserPersonDTO;
import com.equidog.domain.entity.UserEntity;
import com.equidog.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@Component
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class UserRepositoryFacadeImpl implements UserRepositoryFacade {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Autowired
    public UserRepositoryFacadeImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public List<UserEntity> getAllUser() throws ServiceException {
        try {
            return Optional.ofNullable(userRepository.findAll())
                    .orElseThrow(() -> new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "No se encontraron registros."));
        } catch (IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda del usuario.", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general.", e);
        }
    }

    @Override
    public UserPersonDTO getUserById(Long id) throws ServiceException {
        try {
            return Optional.ofNullable(userRepository.findUserEntityById(id)).map(UserConverter::convertToUserDTO)
                    .orElseThrow(() -> new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "No se encontro usuario por el Id"));
        } catch (IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda del usuario.", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general.", e);
        }
    }

    @Override
    public UserPersonDTO findUserAllInfoById(Long id) throws ServiceException {
        try {
            return Optional.ofNullable(userRepository.findUserAllInfoById(id)).map(UserConverter::convertToUserDTOConsult).filter(UserPersonDTO::getIsActive)
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
    public UserPersonDTO createUser(UserPersonDTO entity) throws ServiceException {
        return userConverter.toUserDTO(executeCreateUser(userConverter.convertToUser(entity)));
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserEntity createUser(UserEntity entity) throws ServiceException {
        return executeCreateUser(entity);

    }

    @Override
    public UserPersonDTO updateStatus(Boolean isActive, long id) throws ServiceException {
        try {
            return UserConverter.convertToUserDTO(userRepository.save(userConverter.convertToUser(mapperUserUpdate(isActive, id))));
        } catch (IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda del usuario.", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general.", e);
        }

    }

    @Override
    public UserPersonDTO login(String username, String password) throws ServiceException {
        try {
            return Optional.ofNullable(userRepository.login(username, password)).map(UserConverter::convertToUserDTO).filter(UserPersonDTO::getIsActive)
                    .orElseThrow(() -> new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "No se encontro usuario por el Id"));
        } catch (DataNotFoundPersistenceException | IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda del usuario.", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general.", e);
        }
    }

    @Override
    public void deleteUserById(Long id) throws ServiceException {
        Optional<UserEntity> employee = userRepository.findById(id);
        if (employee.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "El id del usuario no existe");
        }
    }

    @Override
    public UserEntity findByUsername(String username) throws ServiceException {
        try {
            return Optional.ofNullable(userRepository.findByUsername(username))
                    .orElseThrow(() -> new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "No se encontro usuario."));
        } catch (DataNotFoundPersistenceException | IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda del usuario.", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general.", e);
        }
    }


    private UserPersonDTO mapperUserUpdate(Boolean isActive, long id) throws ServiceException {
        UserPersonDTO userActual = getUserById(id);
        userActual.setIsActive(isActive);
        return userActual;
    }

    private UserEntity executeCreateUser(UserEntity entity) throws ServiceException {
        try {
            return userRepository.save(entity);
        } catch (IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda del usuario.", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general.", e);
        }
    }


}
