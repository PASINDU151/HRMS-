package com.Jupiter.hrm.repository;

import com.Jupiter.hrm.config.DbConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeaveTypeRepository {
    private final Connection connection;

    public LeaveTypeRepository() {
        try {
            connection = DbConfig.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateName(long id, String newName) {
        try {
            String query = "UPDATE leave_type SET name = ? WHERE leave_type_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newName);
            preparedStatement.setLong(2, id);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateValue(long id, double value) {
        try {
            String query = "UPDATE leave_type SET pay = ? WHERE leave_type_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, value);
            preparedStatement.setLong(2, id);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
