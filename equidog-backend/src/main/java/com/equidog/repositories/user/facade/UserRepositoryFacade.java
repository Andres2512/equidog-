package com.equidog.repositories.user.facade;

import com.equidog.core.exceptions.base.ServiceException;
import com.equidog.domain.dto.generic.UserPersonDTO;
import com.equidog.domain.entity.UserEntity;

import java.util.List;

public interface UserRepositoryFacade {

    List<UserEntity> getAllUser() throws ServiceException;

    UserPersonDTO getUserById(Long id) throws ServiceException;

    UserPersonDTO findUserAllInfoById(Long id) throws ServiceException;

    UserEntity createUser(UserEntity entity) throws ServiceException;

    UserPersonDTO createUser(UserPersonDTO entity) throws ServiceException;

    UserPersonDTO updateStatus(Boolean isActive, long id) throws ServiceException;

    UserPersonDTO login (String username, String password) throws ServiceException;

    void deleteUserById(Long id) throws ServiceException;

    UserEntity findByUsername(String username) throws ServiceException;
}
