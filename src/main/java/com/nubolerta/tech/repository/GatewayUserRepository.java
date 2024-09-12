package com.nubolerta.tech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nubolerta.tech.entities.GatewayUser;
@Repository
public interface GatewayUserRepository extends JpaRepository<GatewayUser, Long> {
}
