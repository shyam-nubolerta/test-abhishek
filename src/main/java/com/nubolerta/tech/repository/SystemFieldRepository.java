package com.nubolerta.tech.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nubolerta.tech.entities.SystemField;
@Repository
public interface SystemFieldRepository extends JpaRepository<SystemField, Long> {
 
    @Query("SELECT sf FROM SystemField sf JOIN sf.system s WHERE s.name = :name")
    List<SystemField> findSystemFieldsBySystemName(@Param("name") String name);

    @Query("SELECT sf FROM SystemField sf JOIN sf.system s WHERE s.name = :name and sf.name=:fieldName")
    SystemField findByFieldNameAndSystem(@Param("fieldName") String fieldName, @Param("name") String name);
    

}

