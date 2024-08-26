package com.Jupiter.hrm.repository;

import com.Jupiter.hrm.config.DbConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.Jupiter.hrm.entity.EmployeeLeaveBalance;

public class EmployeeLeaveBalanceRepository {
    private final Connection connection;

    public EmployeeLeaveBalanceRepository() {
        try {
            connection = DbConfig.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean setBalance(int balance, long employeeId, long leaveTypeId) {
        try {
            String query = "UPDATE employee_leave_balance SET balance = ? WHERE employee_id = ? and leave_type_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, balance);
            preparedStatement.setLong(2, employeeId);
            preparedStatement.setLong(3, leaveTypeId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
