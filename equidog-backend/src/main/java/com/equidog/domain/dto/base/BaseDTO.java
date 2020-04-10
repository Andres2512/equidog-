package com.equidog.domain.dto.base;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class BaseDTO implements Serializable {

    private String createdBy;

}
