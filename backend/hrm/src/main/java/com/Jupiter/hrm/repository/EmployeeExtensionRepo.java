package com.Jupiter.hrm.repository;

import com.Jupiter.hrm.config.DbConfig;
import com.Jupiter.hrm.entity.Employee;
import com.Jupiter.hrm.entity.EmployeeExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.StringJoiner;

public class EmployeeExtensionRepo {
    Connection connection;
    public EmployeeExtensionRepo() {
        try {
            connection = DbConfig.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addAttribute(String attribute, String constraint){
        String query = "ALTER TABLE employee_extension ADD COLUMN " + attribute + " " + constraint;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            PreparedStatement ps = connection.prepareStatement("SELECT column_name FROM information_schema.columns WHERE table_name = 'employee_extension'");
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                String columnName = resultSet.getString("column_name");
                System.out.println("Column Name: " + columnName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private String createSaveQuery(
            Map<String, Integer> int_attributes,
            Map<String, String> str_attributes,
            Map<String, Double> double_attributes
    ) {
        StringJoiner columns = new StringJoiner(", ");
        StringJoiner placeholders = new StringJoiner(", ");

        for (Map.Entry<String, Integer> entry : int_attributes.entrySet()) {
            columns.add(entry.getKey());
            placeholders.add("?");
        }

        for (Map.Entry<String, String> entry : str_attributes.entrySet()) {
            columns.add(entry.getKey());
            placeholders.add("?");
        }

        for (Map.Entry<String, Double> entry : double_attributes.entrySet()) {
            columns.add(entry.getKey());
            placeholders.add("?");
        }

        return "INSERT INTO employee_extension (" + columns.toString() + ") VALUES (" + placeholders.toString() + ")";
    }
    private PreparedStatement setValues(Map<String, Integer> int_attributes, Map<String, String> str_attributes, Map<String, Double> double_attributes, PreparedStatement preparedStatement){
        try{
            int i = 1;
            for (Map.Entry<String, Integer> entry : int_attributes.entrySet()) {
                Integer key = entry.getValue();
                preparedStatement.setInt(i, key);
                i++;
            }
            for (Map.Entry<String, String> entry : str_attributes.entrySet()) {
                String value = entry.getValue();
                preparedStatement.setString(i, value);
                i++;
            }
            for (Map.Entry<String, Double> entry : double_attributes.entrySet()) {
                Double value = entry.getValue();
                preparedStatement.setDouble(i, value);
                i++;
            }
            return preparedStatement;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void save(Map<String, Integer> int_attributes, Map<String, String> str_attributes, Map<String, Double> double_attributes){
        try{
            String sqlQuery = createSaveQuery(int_attributes, str_attributes, double_attributes);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement = setValues(int_attributes, str_attributes, double_attributes, preparedStatement);
            System.out.println(sqlQuery);
            preparedStatement.executeUpdate();
            PreparedStatement ps = connection.prepareStatement("SELECT column_name FROM information_schema.columns WHERE table_name = 'employee_extension'");
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                String columnName = resultSet.getString("column_name");
                System.out.println("Column Name: " + columnName);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
