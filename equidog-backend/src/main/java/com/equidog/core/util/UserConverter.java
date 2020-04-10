package com.equidog.core.util;

import com.equidog.domain.dto.generic.UserPersonDTO;
import com.equidog.domain.entity.PersonEntity;
import com.equidog.domain.entity.Role;
import com.equidog.domain.entity.UserEntity;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserConverter {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserConverter(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<UserPersonDTO> convertToDTO(Collection<UserEntity> list) {
        return list.stream().map(UserConverter::convertToUserDTO).collect(Collectors.toList());
    }

    public List<UserEntity> convertToEntity(Collection<UserPersonDTO> userPersonDto) {
        return CollectionUtils.isEmpty(userPersonDto) ? Collections.emptyList() : userPersonDto.stream().filter(Objects::nonNull)
                .map(this::convertToUser).collect(Collectors.toList());
    }

    public static UserPersonDTO convertToUserDTO(UserEntity entity) {

        return UserPersonDTO.builder()

                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                // .image(entity.getImage())
//                .documentNumber(entity.getPerson().getDocumentNumber())
                //              .idDocumentType(new DocumentTypeDTO(entity.getPerson().getIdDocumentType().getId(),entity.getPerson().getIdDocumentType().getDescription()))
                .isActive(entity.getIsActive())
                .password(entity.getPassword())
                .username(entity.getUsername())
                .build();
    }

    public UserEntity convertToUser(UserPersonDTO userPersonDTO) {

        return getUser(userPersonDTO);
    }

    public static UserPersonDTO convertToUserDTOConsult(UserEntity entity) {

        return UserPersonDTO.builder()

                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .documentNumber(entity.getPerson().getDocumentNumber())
                //   .idDocumentType(new DocumentTypeDTO(entity.getPerson().getIdDocumentType().getId(),entity.getPerson().getIdDocumentType().getDescription()))
                .isActive(entity.getIsActive())
                .password(entity.getPassword())
                .username(entity.getUsername())
                .build();
    }


    public UserPersonDTO toUserDTO(UserEntity entity) {

        return UserPersonDTO.builder()

                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .documentNumber(entity.getPerson().getDocumentNumber())
                // .idDocumentType(new DocumentTypeDTO(entity.getPerson().getIdDocumentType().getId(),entity.getPerson().getIdDocumentType().getDescription()))
                //  .documentNumber(entity.get)
                .isActive(entity.getIsActive())
                .password(entity.getPassword())
                .username(entity.getUsername())
                .build();
    }

    public static UserEntity toUserEntity(UserPersonDTO userPersonDTO) {

        return getUser(userPersonDTO);
    }

    private static UserEntity getUser(UserPersonDTO userPersonDTO) {
        Calendar date = Calendar.getInstance();

        Role role = new Role();

        UserEntity.UserEntityBuilder user = UserEntity.builder();
        if (ObjectUtils.isNotEmpty(userPersonDTO.getId())) {
            user.id(userPersonDTO.getId());
        }
        if (ObjectUtils.isNotEmpty(userPersonDTO.getIsActive())) {
            user.isActive(userPersonDTO.getIsActive());
        } else {
            user.isActive(true);
        }
        if (ObjectUtils.isNotEmpty(userPersonDTO.getRole())) {
            role.setId(userPersonDTO.getRole().getId());
            role.setDescription(userPersonDTO.getRole().getDescription());
        } else {
            role.setId(3);
            role.setDescription("Cliente");
        }
        user.createdAt(date)
                .password(
                        //  bCryptPasswordEncoder.encode
                        (userPersonDTO.getPassword()))
                .username(userPersonDTO.getUsername())
                .role(role);
        return user.build();
    }

    public static PersonEntity convertToPerson(UserPersonDTO userPersonDTO) {
        PersonEntity personEntity = PersonEntity.builder()
                .idDocumentType(userPersonDTO.getDocumentType())
                .documentNumber(userPersonDTO.getDocumentNumber())
                .address1(userPersonDTO.getAddress())
                .address2(userPersonDTO.getAddress())
                .createdAt(userPersonDTO.getCreatedAt())
                .email(userPersonDTO.getEmail())
                .name(userPersonDTO.getName())
                .surname(userPersonDTO.getSurname())
                .phone1(userPersonDTO.getPhone())
                .phone2(userPersonDTO.getPhone())
                .image(userPersonDTO.getImage())
                .build();
        return personEntity;

    }

    public static UserPersonDTO toPersonDTO(PersonEntity entity) {

        return UserPersonDTO.builder()

                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .documentNumber(entity.getDocumentNumber())
                .documentType(entity.getIdDocumentType())
                .city(entity.getIdCity().getId())
                .user(entity.getUser().getId())
                .build();
    }

}
