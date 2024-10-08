package com.nubolerta.tech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nubolerta.tech.entities.GatewayUser;
import com.nubolerta.tech.service.GatewayUserService;

@RestController
@RequestMapping("/api/users")
public class UsersController {
  
  private GatewayUserService gatewayUserService;

  @Autowired
  public UsersController(GatewayUserService gatewayUserService) {
    this.gatewayUserService = gatewayUserService;
  }

  @GetMapping(value = "")
  ResponseEntity<List<GatewayUser>> getUsers() {
    return ResponseEntity.ok().body(gatewayUserService.getAll());
  }
}
