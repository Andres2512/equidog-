package com.equidog.domain.dto.response;

import com.equidog.domain.dto.generic.UserPersonDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@ApiModel(description = "Info User", value = "UserDTO info")
public class UserResponseDTO extends UserPersonDTO {



    public UserResponseDTO(String username, String password) {
        super(username,password);
    }


}
