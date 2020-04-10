/*
 * @Author: $author
 * @Date: 12/02/2020
 * @Version:
 */
package com.equidog.repositories.person;

import com.equidog.domain.entity.PersonEntity;
import com.equidog.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The Interface UserRepository.
 */
@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    @Query("SELECT p FROM PersonEntity p where p.id=:ID")
    PersonEntity findPersonEntityById(@Param("ID") long id);

    @Query("SELECT p FROM PersonEntity p inner join p.user WHERE p.documentNumber=:DOCUMENT_NUMBER")
    PersonEntity findPersonAllInfoById(@Param("DOCUMENT_NUMBER") String documentNumber);

}
