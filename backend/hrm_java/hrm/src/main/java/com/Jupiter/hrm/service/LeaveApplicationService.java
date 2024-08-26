package com.Jupiter.hrm.service;

import com.Jupiter.hrm.entity.LeaveApplication;
import com.Jupiter.hrm.repository.LeaveApplicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveApplicationService {

    private LeaveApplicationRepo leaveApplicationRepo;

    @Autowired
    public LeaveApplicationService(LeaveApplicationRepo leaveApplicationRepo){
        this.leaveApplicationRepo = leaveApplicationRepo;
    }


    public boolean newApplication(LeaveApplication leaveApplication){
        if(leaveApplicationRepo.newApplication(leaveApplication)){
            return true;
        }
        else {
            return false;
        }

    }
    public boolean updateApplication(LeaveApplication leaveApplication){
        if(leaveApplicationRepo.updateApplication(leaveApplication)){
            return true;
        }
        else {
            return false;
        }
    }

}
