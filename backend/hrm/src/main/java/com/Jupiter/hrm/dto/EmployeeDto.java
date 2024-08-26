package com.Jupiter.hrm.dto;

import lombok.Data;

import javax.persistence.Id;
import java.util.HashMap;
import java.util.Map;

@Data
public class EmployeeDto {
    @Id
    private int employee_id;
    private String name;
    private String birthdate;
    private boolean marital_status;
    private String emergency_contact;
    private String organization_id;
    private Map<String, Integer> int_attributes = new HashMap<>();
    private Map<String, String> str_attributes = new HashMap<>();
    private Map<String, Double> double_attributes = new HashMap<>();

}
