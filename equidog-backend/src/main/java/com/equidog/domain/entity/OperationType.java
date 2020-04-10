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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Harold Andres Horta
 */
@Data
@Entity
@Table(name = "operation_type", catalog = "equidog")

public class OperationType extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "operationTypeId", fetch = FetchType.LAZY)
    private List<Sell> sellList;
    @OneToMany(mappedBy = "operationTypeId", fetch = FetchType.LAZY)
    private List<Operation> operationList;

}
