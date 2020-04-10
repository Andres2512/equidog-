/*
 * @Author: $author
 * @Date: 12/02/2020
 * @Version:
 */
package com.equidog.repositories.user;

import com.equidog.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The Interface UserRepository.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u where u.id=:ID")
    UserEntity findUserEntityById(@Param("ID") long id);

    @Query("SELECT u FROM UserEntity u inner join u.person WHERE u.id=:ID")
    UserEntity findUserAllInfoById(@Param("ID") long id);

    @Query("SELECT U FROM UserEntity U LEFT JOIN U.person WHERE U.username=:USERNAME AND U.password=:PASSWORD")
    UserEntity login(@Param("USERNAME") String username, @Param("PASSWORD") String password);

    UserEntity findByUsername(String username);
}
