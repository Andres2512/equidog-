/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equidog.domain.entity;

import com.equidog.domain.entity.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Harold Andres Horta
 */
@Data
@Entity
@Table(name = "sell", catalog = "equidog")
public class Sell extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private Double total;
    @Column(name = "cash")
    private Double cash;
    @Column(name = "discount")
    private Double discount;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "box_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Box boxId;
    @JoinColumn(name = "operation_type_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private OperationType operationTypeId;
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PersonEntity personId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity userEntityId;
    @OneToMany(mappedBy = "sellId", fetch = FetchType.LAZY)
    private List<Operation> operationList;


}
