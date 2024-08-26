package com.Jupiter.hrm.service;

import com.Jupiter.hrm.entity.LeaveType;
import com.Jupiter.hrm.repository.LeaveTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class LeaveTypeService {
    @Autowired
    private LeaveTypeRepository leaveTypeRepository;


    public boolean updateName(LeaveType updatedLeaveType){
        if(leaveTypeRepository.updateName(updatedLeaveType.getLeave_type_id(), updatedLeaveType.getName())){
            return true;
        }
        else {
            return false;
        }

    }
    public boolean updateValue(LeaveType updadtedLeaveType){
        if(leaveTypeRepository.updateValue(updadtedLeaveType.getLeave_type_id(), updadtedLeaveType.getPay())){
            return true;
        }
        else {
            return false;
        }
    }

}
