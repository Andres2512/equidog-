/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equidog.domain.entity;

import com.equidog.domain.entity.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Harold Andres Horta
 */
@Data
@Entity
@Table(name = "configuration", catalog = "equidog")

public class Configuration extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Column(name = "kind")
    private Integer kind;
    @Size(max = 255)
    @Column(name = "val")
    private String val;


}
