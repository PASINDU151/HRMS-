package com.Jupiter.hrm.dto;

import lombok.Data;

import javax.persistence.Id;

@Data
public class UserDto {
    @Id
    private long user_id;
    private String username;
    private String password;
    private String employee_id;
}
