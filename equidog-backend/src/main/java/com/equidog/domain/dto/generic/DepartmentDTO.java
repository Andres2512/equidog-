package com.equidog.domain.dto.generic;

import com.equidog.domain.dto.base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO extends BaseDTO {

    private String description;
    private Integer code;

}
