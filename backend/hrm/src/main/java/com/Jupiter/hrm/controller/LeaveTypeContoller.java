package com.Jupiter.hrm.controller;
import com.Jupiter.hrm.dto.LeaveTypeDto;
import com.Jupiter.hrm.dto.UserDto;
import com.Jupiter.hrm.entity.LeaveType;
import com.Jupiter.hrm.entity.User;
import com.Jupiter.hrm.service.LeaveTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/leavetype")
public class LeaveTypeContoller {
    private final LeaveTypeService leaveTypeService;

    @Autowired
    public LeaveTypeContoller(LeaveTypeService leaveTypeService){
        this.leaveTypeService = leaveTypeService;
    }

    @Autowired
    private ModelMapper modelMapper;

    @PutMapping("/update-name")
    public HttpStatus updateName(@RequestBody LeaveTypeDto updatedLeaveType) {
        System.out.println(updatedLeaveType.getLeave_type_id() + updatedLeaveType.getName());
        if(leaveTypeService.updateName(modelMapper.map(updatedLeaveType, LeaveType.class))){
            return HttpStatus.OK;
        }
        else{
            return HttpStatus.NOT_FOUND;
        }
    }

    @PutMapping("/update-pay")
    public HttpStatus updatePay(@RequestBody LeaveTypeDto updatedLeaveType) {
        if(leaveTypeService.updateValue(modelMapper.map(updatedLeaveType, LeaveType.class))){
            return HttpStatus.OK;
        }
        else{
            return HttpStatus.NOT_FOUND;
        }
    }
}
