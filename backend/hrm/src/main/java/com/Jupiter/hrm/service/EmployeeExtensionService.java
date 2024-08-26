package com.Jupiter.hrm.service;
import com.Jupiter.hrm.entity.Employee;
import com.Jupiter.hrm.repository.EmployeeExtensionRepo;
import com.Jupiter.hrm.repository.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class EmployeeExtensionService {
    @Autowired
    private EmployeeExtensionRepo employeeExtensionRepo;


    public void createEmployeeExtension(Map<String, Integer> int_attributes, Map<String, String> str_attributes, Map<String, Double> double_attributes){
        employeeExtensionRepo.save(int_attributes, str_attributes, double_attributes);
    }
    public void addAttributes(String attribute, String constraint){
        employeeExtensionRepo.addAttribute(attribute, constraint);
    }

}
