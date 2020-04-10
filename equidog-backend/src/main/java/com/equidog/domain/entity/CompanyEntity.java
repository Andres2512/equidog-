package com.equidog.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "company" , catalog = "equidog")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})

public class CompanyEntity implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    @Column(name = "nit", nullable = false, unique = true ,updatable = false)
    private String nit;
    private String name;
    private String phone;
    private String address;
    @NotNull
    @Column(name = "is_active", insertable = true)
    private Boolean isActive;
}
