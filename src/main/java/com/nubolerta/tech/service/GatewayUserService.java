package com.nubolerta.tech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nubolerta.tech.entities.GatewayUser;
import com.nubolerta.tech.repository.GatewayUserRepository;

@Service
public class GatewayUserService {

private GatewayUserRepository gatewayUserRepository;

@Autowired
public GatewayUserService(GatewayUserRepository gatewayUserRepository) {
  this.gatewayUserRepository = gatewayUserRepository;
}

public List<GatewayUser> getAll() {
  return gatewayUserRepository.findAll();
}

}
