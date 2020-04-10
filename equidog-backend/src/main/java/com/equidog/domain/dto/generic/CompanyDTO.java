package com.equidog.domain.dto.generic;


import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "Info company", value = "CompanyDTO info")
public class CompanyDTO {

    private static final long serialVersionUID = 1L;
    @Schema(name = "id", example = "1")
    private Long id;
    @Schema(name = "name", example = "Equidog")
    private String name;
    @Schema(name = "phone", example = "312452356")
    private String phone;
    @Schema(name = "address", example = "Calle 45")
    private String address;
    @Schema(name = "isActive", example = "1")
    private Boolean isActive;
    @Schema(name = "nit", example = "12345678")
    private String nit;
}
