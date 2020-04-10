package com.equidog.services.user.impl;

import com.equidog.core.exceptions.base.ServiceException;
import com.equidog.core.util.UserConverter;
import com.equidog.domain.dto.generic.UserPersonDTO;
import com.equidog.domain.dto.request.UserRequestDTO;
import com.equidog.domain.dto.response.UserResponseDTO;
import com.equidog.domain.entity.CityEntity;
import com.equidog.domain.entity.CompanyEntity;
import com.equidog.domain.entity.PersonEntity;
import com.equidog.domain.entity.UserEntity;
import com.equidog.repositories.city.facade.CityRepositoryFacade;
import com.equidog.repositories.company.facade.CompanyRepositoryFacade;
import com.equidog.repositories.person.facade.PersonRepositoryFacade;
import com.equidog.repositories.user.facade.UserRepositoryFacade;
import com.equidog.services.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class UserServiceImpl implements UserService {

    private final UserRepositoryFacade userRepositoryFacade;

    private final PersonRepositoryFacade personRepositoryFacade;

    private final CityRepositoryFacade cityRepositoryFacade;

    private final CompanyRepositoryFacade companyRepositoryFacade;

    public UserServiceImpl(UserRepositoryFacade userRepositoryFacade, PersonRepositoryFacade personRepositoryFacade, CityRepositoryFacade cityRepositoryFacade, CompanyRepositoryFacade companyRepositoryFacade) {
        this.userRepositoryFacade = userRepositoryFacade;
        this.personRepositoryFacade = personRepositoryFacade;
        this.cityRepositoryFacade = cityRepositoryFacade;
        this.companyRepositoryFacade = companyRepositoryFacade;
    }


    @Override
    public List<UserPersonDTO> getAllUser() throws ServiceException {

        List<UserEntity> personList = userRepositoryFacade.getAllUser();
        return personList.stream().map(this::mapperUserEntity).collect(Collectors.toList());
    }

    private UserPersonDTO mapperUserEntity(UserEntity entity) {
        return UserPersonDTO.builder().username(entity.getUsername()).password(entity.getPassword()).build();
    }

    @Override
    public UserPersonDTO getUserById(Long id) throws ServiceException {
        return userRepositoryFacade.getUserById(id);
    }

    @Override
    public UserPersonDTO getUserAllInfoById(Long id) throws ServiceException {
        return userRepositoryFacade.findUserAllInfoById(id);
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequest) throws ServiceException {
        UserEntity userCreated = userRepositoryFacade.createUser(UserConverter.toUserEntity(userRequest));
        return new UserResponseDTO(userCreated.getUsername(), userCreated.getPassword());
    }

    public UserResponseDTO registerPersonalDataUser(UserRequestDTO userRequest) throws ServiceException {
        CityEntity city = cityRepositoryFacade.getCityById(userRequest.getCity());
        CompanyEntity company = companyRepositoryFacade.getCompanyById(userRequest.getId());
        UserEntity userEntity = userRepositoryFacade.findByUsername(userRequest.getUsername());
        PersonEntity personalDataUser = mapperEntityCreateDataPersonal(userRequest, city, company, userEntity);
        PersonEntity personEntity = personRepositoryFacade.createPerson(personalDataUser);
        return mapperUserDataPersonal(personEntity);
    }

    @Override
    public UserPersonDTO updateStatusUser(Boolean isActive, long id) throws ServiceException {
        return userRepositoryFacade.updateStatus(isActive, id);
    }

    @Override
    public UserPersonDTO login(String username, String password) throws ServiceException {
        return userRepositoryFacade.login(username, password);
    }


    @Override
    public void deleteUser(Long id) throws ServiceException {
        userRepositoryFacade.deleteUserById(id);
    }


    private PersonEntity mapperEntityCreateDataPersonal(UserRequestDTO userRequest, CityEntity city, CompanyEntity company, UserEntity userEntity) {
        PersonEntity personalDataUser = UserConverter.convertToPerson(userRequest);
        personalDataUser.setUser(userEntity);
        personalDataUser.setIdCity(city);
        personalDataUser.setIdCompany(company);
        return personalDataUser;
    }

    private UserResponseDTO mapperUserDataPersonal(PersonEntity personEntity) {
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setUsername(personEntity.getUser().getUsername());
        responseDTO.setDocumentType(personEntity.getIdDocumentType());
        responseDTO.setDocumentNumber(personEntity.getDocumentNumber());
        return responseDTO;
    }

}
