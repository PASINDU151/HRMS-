package com.Jupiter.hrm.entity;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Data
public class EmployeeLeaveBalance {

    @EmbeddedId
    private EmployeeLeaveBalanceId id;
    private int balance;

}

