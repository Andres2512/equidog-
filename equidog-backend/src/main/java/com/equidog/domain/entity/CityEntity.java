/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equidog.domain.entity;

import com.equidog.domain.entity.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Harold Andres Horta
 */
@Data
@Entity
@Table(name = "city", catalog = "equidog")
public class CityEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(max = 100)
    @Column(name = "description")
    private String description;
    @Column(name = "codigo")
    private Integer code;
    @JoinColumn(name = "id_department", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Department idDepartment;

}
