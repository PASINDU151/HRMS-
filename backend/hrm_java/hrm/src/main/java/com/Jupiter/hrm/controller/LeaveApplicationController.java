package com.Jupiter.hrm.controller;
import com.Jupiter.hrm.dto.LeaveApplicationDto;
import com.Jupiter.hrm.entity.LeaveApplication;
import com.Jupiter.hrm.service.LeaveApplicationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/leaveapplication")
public class LeaveApplicationController {
    private final LeaveApplicationService leaveApplicationService;
    private  final ModelMapper modelMapper;

    @Autowired
    public LeaveApplicationController(LeaveApplicationService leaveApplicationService, ModelMapper modelMapper){
        this.leaveApplicationService = leaveApplicationService;
        this.modelMapper = modelMapper;
    }



    @PutMapping("/update-application")
    public HttpStatus updateApplication(@RequestBody LeaveApplicationDto updatedLeaveApplication) {
        if(leaveApplicationService.updateApplication(modelMapper.map(updatedLeaveApplication, LeaveApplication.class))){
            return HttpStatus.OK;
        }
        else{
            return HttpStatus.NOT_FOUND;
        }
    }

    @PostMapping("/new-application")
    public HttpStatus newApplication(@RequestBody LeaveApplicationDto updatedLeaveApplication) {
        if(leaveApplicationService.newApplication(modelMapper.map(updatedLeaveApplication, LeaveApplication.class))){
            return HttpStatus.OK;
        }
        else{
            return HttpStatus.NOT_FOUND;
        }
    }
}
