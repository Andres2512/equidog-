package com.equidog.repositories.city;

import com.equidog.domain.entity.CityEntity;
import com.equidog.domain.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityEntity,Long> {
    @Query("SELECT c FROM CityEntity c where c.id=:ID")
    CityEntity findCityEntityById(@Param("ID") long id);
}
