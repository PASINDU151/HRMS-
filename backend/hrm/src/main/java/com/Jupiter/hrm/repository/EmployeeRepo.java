package com.Jupiter.hrm.repository;

import com.Jupiter.hrm.entity.Employee;
import com.Jupiter.hrm.config.DbConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepo {
    Connection connection;
    public EmployeeRepo() {
        try {
            connection = DbConfig.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void save(Employee employee){
        try{
            String sqlQuery = "INSERT INTO employee (name, birthdate, marital_status, emergency_contact, organization_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getBirthdate());
            preparedStatement.setBoolean(3, employee.isMarital_status());
            preparedStatement.setString(4, employee.getEmergency_contact());
            preparedStatement.setString(5, employee.getOrganization_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Employee> findAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();

        try {
            String sqlQuery = "SELECT * FROM employee";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                employeeList.add(getEmployee(new Employee(), resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeList;
    }

    public Employee findByEmployeenameAndPassword(String employeename, String password) {
        Employee employee = null;

        try {
            String sqlQuery = "SELECT * FROM employee WHERE employeename = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, employeename);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                employee = getEmployee(new Employee(), resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employee;
    }

    public Optional<Employee> findById(Long employeeID){
        Employee employee = null;

        try {
            String sqlQuery = "SELECT * FROM employee WHERE employee_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setLong(1, employeeID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                employee = getEmployee(new Employee(), resultSet);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.ofNullable(employee);
    }

    public void delete(Employee employee){
        try {
            String sqlQuery = "DELETE FROM employee WHERE employee_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, employee.getEmployee_id());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private Employee getEmployee(Employee employee, ResultSet resultSet){
        try {
            employee.setEmployee_id(resultSet.getInt("employee_id"));
            employee.setName(resultSet.getString("employeename"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    public void update(Employee employee) {
        try {
            String sqlQuery = "UPDATE employee SET employeename = ?, password = ? where employee_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setInt(3, employee.getEmployee_id());
            preparedStatement.executeUpdate();
            int rowsUpdated = preparedStatement.executeUpdate();

            System.out.println(rowsUpdated);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
