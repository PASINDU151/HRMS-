package com.Jupiter.hrm.dto;

import lombok.Data;

@Data
public class EmployeeLeaveBalanceDto {
    Long employee_id;
    Long leave_type_id;
    int balance;
}
