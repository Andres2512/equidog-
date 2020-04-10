package com.equidog.domain.dto.request;

import com.equidog.domain.dto.generic.UserPersonDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "Info User", value = "UserDTO info")
public class UserRequestDTO extends UserPersonDTO {

}
