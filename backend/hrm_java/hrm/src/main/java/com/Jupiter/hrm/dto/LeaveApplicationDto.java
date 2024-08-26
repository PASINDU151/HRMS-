package com.Jupiter.hrm.dto;

import lombok.Data;

@Data
public class LeaveApplicationDto {
    private long application_id;
    private long employee_id;
    private long leave_type_id;
    private String start_date;
    private String end_date;
    private String status;
}
