package com.nubolerta.tech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemRepository extends JpaRepository<com.nubolerta.tech.entities.System, Long> {

   @Query("SELECT sf FROM System sf WHERE sf.name = :name")
   com.nubolerta.tech.entities.System findByName(@Param("name") String name);
  
}
