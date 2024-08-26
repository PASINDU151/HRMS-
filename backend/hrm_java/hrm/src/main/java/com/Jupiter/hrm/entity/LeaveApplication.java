package com.Jupiter.hrm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@Entity
public class LeaveApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long application_id;
    private long employee_id;
    private long leave_type_id;
    private String start_date;
    private String end_date;
    private String status;

    public long getApplication_id() {
        return application_id;
    }


    public long getEmployee_id() {
        return employee_id;
    }

    public long getLeave_type_id() {
        return leave_type_id;
    }


    public String getStart_date() {
        return start_date;
    }


    public String getEnd_date() {
        return end_date;
    }


    public String getStatus() {
        return status;
    }

}
