package com.Jupiter.hrm.service;
import com.Jupiter.hrm.entity.Employee;
import com.Jupiter.hrm.repository.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;
import java.util.Optional;
@Service
public class EmployeeService {


//    public static Connection getConnection() throws SQLException {
//        Connection con = null;
//        try {
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jupiter", "root", "30104771");
//            System.out.println("Connection success");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw e;
//        }
//        return con;
//    }



    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeeRepo employeeRepo;

    public void createEmployee(Employee employee){
        employeeRepo.save(employee);
    }

    public List<Employee> getAllEmployees(){return  employeeRepo.findAllEmployees();}

    public Employee getEmployeeById(Long id){
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        return optionalEmployee.orElse(null);
    }

    public boolean updateEmployee(Long id, Employee updatedEmployee) {
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employeeRepo.update(updatedEmployee);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean deleteEmployee(Long id){
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        if(optionalEmployee.isPresent()){
            employeeRepo.delete(optionalEmployee.get());
            return true;
        }
        else{
            return false;
        }
    }

    public Employee findByEmployeenameAndPassword(String employeename, String password) {
        Employee employee = employeeRepo.findByEmployeenameAndPassword(employeename, password);
        if(employee == null){
            return null;
        }
        else{
            return employee;
        }
    }
}
