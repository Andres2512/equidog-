package com.equidog.domain.dto.generic;

import com.equidog.domain.dto.base.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel(description = "Info Role", value = "RoleDTO info")
public class RoleDTO{

    @Schema(name = "id", example = "1")
    private int id;
    @Schema(name = "description", example = "Administrador")
    private String description;


}
