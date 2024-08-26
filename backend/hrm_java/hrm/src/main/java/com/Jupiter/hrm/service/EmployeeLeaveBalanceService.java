package com.Jupiter.hrm.service;
import com.Jupiter.hrm.dto.EmployeeLeaveBalanceDto;
import com.Jupiter.hrm.repository.EmployeeLeaveBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeLeaveBalanceService {

    private EmployeeLeaveBalanceRepository employeeLeaveBalanceRepository;

    @Autowired
    public EmployeeLeaveBalanceService(EmployeeLeaveBalanceRepository employeeLeaveBalanceRepository){
        this.employeeLeaveBalanceRepository = employeeLeaveBalanceRepository;
    }


    public boolean updateBalance(EmployeeLeaveBalanceDto employeeLeaveBalance){

        if(employeeLeaveBalanceRepository.setBalance(
                employeeLeaveBalance.getBalance(),
                employeeLeaveBalance.getEmployee_id(),
                employeeLeaveBalance.getLeave_type_id())){
            return true;
        }
        else{
            return false;
        }
    }
    public void addAttributes(String attribute, String constraint){

    }

}