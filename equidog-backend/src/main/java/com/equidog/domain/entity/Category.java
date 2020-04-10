package com.equidog.domain.entity;

import com.equidog.domain.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Harold Andres Horta
 */
@Data
@Entity
@Table(name = "category", catalog = "equidog")
public class Category extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(max = 255)
    @Column(name = "image")
    private String image;

    @Size(max = 50)
    @Column(name = "name")
    private String name;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToMany(mappedBy = "categoryId", fetch = FetchType.LAZY)
    private List<Product> productList;

}
