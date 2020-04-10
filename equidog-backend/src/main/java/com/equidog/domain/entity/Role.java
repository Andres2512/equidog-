package com.equidog.domain.entity;


import com.equidog.domain.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "role", catalog = "equidog")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Role extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String description;

}
