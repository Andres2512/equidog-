package com.equidog.repositories.company;

import com.equidog.domain.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity,Long> {

    @Query("SELECT c FROM CompanyEntity c WHERE c.id =:ID ")
    CompanyEntity findCompanyEntitiesById (@Param("ID") long id);
}
