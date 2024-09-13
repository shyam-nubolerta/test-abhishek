package com.nubolerta.tech.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "system")
public class System {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SYSTEM_SEQ")
    @SequenceGenerator(name = "SYSTEM_SEQ", sequenceName = "SYSTEM_SEQ", allocationSize = 1)
    private Long id;

    private String name;

    private String url;

    // One-to-Many relationship with Employee
    @OneToMany(mappedBy = "system", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SystemField> systemFields;

    // Constructors, Getters, and Setters
    public System() {}

    public System(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SystemField> getSystemFields() {
        return systemFields;
    }

    public void setSystemFields(List<SystemField> systemFields) {
        this.systemFields = systemFields;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

