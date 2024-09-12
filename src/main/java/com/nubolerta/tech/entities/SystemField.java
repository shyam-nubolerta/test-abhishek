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
@Table(name = "SYSTEM_FIELD")
public class SystemField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="field_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "system_id") 
    private com.nubolerta.tech.entities.System system;

    public SystemField() {}

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

    public com.nubolerta.tech.entities.System getSystem() {
        return system;
    }

    public void setSystem(com.nubolerta.tech.entities.System system) {
        this.system = system;
    }
}
