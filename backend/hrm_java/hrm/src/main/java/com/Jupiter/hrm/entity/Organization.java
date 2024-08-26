package com.Jupiter.hrm.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long organization_id;
    private String name;
    private String address;
    private String registration_number;

    public long getOrganization_id() {
        return organization_id;
    }


    public void setOrganization_id(long organizationId) {
        this.organization_id = organizationId;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegistration_number() {
        return registration_number;
    }


    public void setRegistration_number(String registrationNumber) {
        this.registration_number = registrationNumber;
    }
}
