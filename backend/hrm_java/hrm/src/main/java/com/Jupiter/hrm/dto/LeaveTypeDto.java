package com.Jupiter.hrm.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class LeaveTypeDto {
    @Id
    private long leave_type_id;
    private String name;
    private double pay;

}