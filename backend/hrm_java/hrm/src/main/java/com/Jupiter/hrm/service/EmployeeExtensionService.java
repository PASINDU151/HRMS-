package com.Jupiter.hrm.service;
import com.Jupiter.hrm.entity.Employee;
import com.Jupiter.hrm.entity.EmployeeExtension;
import com.Jupiter.hrm.repository.EmployeeExtensionRepo;
import com.Jupiter.hrm.repository.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeExtensionService {

    private EmployeeExtensionRepo employeeExtensionRepo;

    @Autowired
    public EmployeeExtensionService(EmployeeExtensionRepo employeeExtensionRepo){
        this.employeeExtensionRepo = employeeExtensionRepo;
    }


    public void createEmployeeExtension(Map<String, Integer> int_attributes, Map<String, String> str_attributes, Map<String, Double> double_attributes, Long id){
        employeeExtensionRepo.save(int_attributes, str_attributes, double_attributes, id);
    }
    public void addAttributes(String attribute, String constraint){
        employeeExtensionRepo.addAttribute(attribute, constraint);
    }

    public Set<String> getAttributes(){
        try {
            return employeeExtensionRepo.getAttributes();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
