package com.nubolerta.tech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nubolerta.tech.entities.SystemUserFieldValue;

@Repository
public interface SystemUserFieldValueRepository extends JpaRepository<SystemUserFieldValue, Long>{
  
}
