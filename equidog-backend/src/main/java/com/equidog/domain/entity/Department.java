/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equidog.domain.entity;

import com.equidog.domain.entity.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Harold Andres Horta
 */
@Data
@Entity
@Table(name = "department", catalog = "equidog")

public class Department extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Column(name = "codigo")
    private Integer code;
    @OneToMany(mappedBy = "idDepartment", fetch = FetchType.LAZY)
    private List<CityEntity> cityList;


}
