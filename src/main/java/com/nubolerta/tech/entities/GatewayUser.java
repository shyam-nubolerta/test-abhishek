package com.nubolerta.tech.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "GATEWAY_USER")
public class GatewayUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GATEWAY_USER_SEQ")
    @SequenceGenerator(name = "GATEWAY_USER_SEQ", sequenceName = "GATEWAY_USER_SEQ", allocationSize = 1)
    private Long id;
    
    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
}
