package com.Jupiter.hrm.dto;

import lombok.Data;

import javax.persistence.Id;

@Data
public class OrganizationDto {
    @Id
    private long organization_id;
    private String name;
    private String address;
    private String registration_number;
}
