package com.nubolerta.tech.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "GATEWAY_USER_SYSTEM_FIELD_VALUE")
public class SystemUserFieldValue {

  public SystemUserFieldValue() {

  }
  
  public SystemUserFieldValue(Long id, GatewayUser user, SystemField systemField, String value) {
    this.id = id;
    this.user = user;
    this.systemField = systemField;
    this.value = value;
  }
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private GatewayUser user;

  @ManyToOne
  @JoinColumn(name = "user_system_field_id") 
  private SystemField systemField;

  @Column(name="field_value")
  private String value;
}
