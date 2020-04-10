package com.equidog.domain.dto.generic;

import com.equidog.domain.entity.*;
import com.equidog.domain.enums.DocumentType;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Calendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "Info User", value = "UserDTO info")
public class UserPersonDTO {

    @Schema(name = "id", example = "11234")
    protected Long id;
    @Schema(name = "address", example = "Carrera 17 # 53-79")
    protected String address;
    @Schema(name = "createdAt", example = "2020-04-04")
    protected Calendar createdAt;
    @Schema(name = "documentNumber", example = "1075248593")
    protected String documentNumber;
    @Schema(name = "email", example = "hahorta@misena.edu.co")
    protected String email;
    @Schema(name = "image", example = "1.jpg")
    protected String image;
    @Schema(name = "isActive", example = "1")
    protected Boolean isActive;
    @Schema(name = "name", example = "Harold")
    protected String name;
    @Schema(name = "password", example = "1234")
    protected String password;
    @Schema(name = "phone", example = "3132498508")
    protected String phone;
    @Schema(name = "surname", example = "Horta")
    protected String surname;
    @Schema(name = "username", example = "hahorta")
    protected String username;
    @Schema(name = "idCompany")
    @JsonProperty("idCompany")
    protected CompanyDTO idCompany;
    @Schema(name = "documentType")
    protected DocumentType documentType;
    @Schema(name = "city", example = "1")
    protected Long city;
    @Schema(name = "department", example = "1")
    protected Long department;
    @Schema(name = "role")
    protected RoleDTO role;
    @Schema(name = "user", example = "1")
    protected Long user;

    public UserPersonDTO(String username, String password) {

    }

}
