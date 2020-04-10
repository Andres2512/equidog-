/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equidog.domain.entity;

import com.equidog.domain.enums.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * @author Harold Andres Horta
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person", catalog = "equidog")
public class PersonEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @Enumerated(EnumType.ORDINAL)
    private DocumentType idDocumentType;

    @Column(name = "document_number", unique = true, nullable = false)
    private String documentNumber;

    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 50)
    @Column(name = "surname")
    private String surname;
    @Size(max = 50)
    @Column(name = "address1")
    private String address1;
    @Size(max = 50)
    @Column(name = "address2")
    private String address2;
    @Size(max = 50)
    @Column(name = "phone1")
    private String phone1;
    @Size(max = 50)
    @Column(name = "phone2")
    private String phone2;
    @Size(max = 50)
    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @Column(name = "image")
    private String image;
    @JoinColumn(name = "id_company")
    @ManyToOne(fetch = FetchType.LAZY)
    private CompanyEntity idCompany;
    @JoinColumn(name = "id_city", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CityEntity idCity;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdAt;
    @OneToMany(mappedBy = "personId", fetch = FetchType.LAZY)
    private List<Sell> sellList;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_USER", updatable = false, nullable = false, unique = true)
    private UserEntity user;

}
