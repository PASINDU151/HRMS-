package com.Jupiter.hrm.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class EmployeeLeaveBalanceId implements Serializable {

    @Column(name = "employee_id")
    private Long employee_id;
    @Column(name = "leave_type_id")
    private Long leave_type_id;

}
