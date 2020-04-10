package com.equidog.services.user;

import com.equidog.core.exceptions.base.ServiceException;
import com.equidog.domain.dto.generic.UserPersonDTO;
import com.equidog.domain.dto.request.UserRequestDTO;
import com.equidog.domain.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {

    List<UserPersonDTO> getAllUser() throws ServiceException;

    UserPersonDTO getUserById(Long id) throws ServiceException;

    UserPersonDTO getUserAllInfoById(Long id) throws ServiceException;

    UserResponseDTO createUser(UserRequestDTO entity) throws ServiceException;

    UserPersonDTO updateStatusUser(Boolean isActive, long id) throws ServiceException;

    UserPersonDTO login(String username, String password) throws ServiceException;

    void deleteUser(Long id) throws ServiceException;

    UserResponseDTO registerPersonalDataUser(UserRequestDTO userRequest) throws ServiceException;
}
