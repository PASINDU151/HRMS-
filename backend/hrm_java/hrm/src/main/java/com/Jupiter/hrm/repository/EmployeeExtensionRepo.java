package com.Jupiter.hrm.repository;

import com.Jupiter.hrm.config.DbConfig;
import com.Jupiter.hrm.entity.Employee;
import com.Jupiter.hrm.entity.EmployeeExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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
        placeholders.add("?");
        String query = "INSERT INTO employee_extension (" +"employee_id, " + columns.toString() + ") VALUES (" + placeholders.toString() + ")";
        return query;
    }
    private PreparedStatement setValues(Map<String, Integer> int_attributes, Map<String, String> str_attributes, Map<String, Double> double_attributes, Long id, PreparedStatement preparedStatement){
        try{
            preparedStatement.setLong(1, id);
            int i = 2;
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

    public void save(Map<String, Integer> int_attributes, Map<String, String> str_attributes, Map<String, Double> double_attributes, Long id){
        try{
            if(validateAttributes(int_attributes, str_attributes, double_attributes)){
                String sqlQuery = createSaveQuery(int_attributes, str_attributes, double_attributes);
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement = setValues(int_attributes, str_attributes, double_attributes, id, preparedStatement);
                System.out.println(sqlQuery);
                preparedStatement.executeUpdate();
            }

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
    private boolean validateAttributes(Map<String, Integer> int_attributes, Map<String, String> str_attributes, Map<String, Double> double_attributes) {
        try {
            Set<String> columnNames = new HashSet<>();
            columnNames = getAttributes();


            for (Map.Entry<String, Integer> entry : int_attributes.entrySet()) {
                if (!columnNames.contains(entry.getKey())) {
                    return false;
                }
            }

            for (Map.Entry<String, String> entry : str_attributes.entrySet()) {
                if (!columnNames.contains(entry.getKey())) {
                    return false;
                }
            }

            for (Map.Entry<String, Double> entry : double_attributes.entrySet()) {
                if (!columnNames.contains(entry.getKey())) {
                    return false;
                }
            }

            return true; // All attribute keys are present in column names
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<String> getAttributes() throws SQLException{
        PreparedStatement ps = null;
        Set<String> columnNames = new HashSet<>();
        try {
            ps = connection.prepareStatement("SELECT column_name FROM information_schema.columns WHERE table_name = 'employee_extension'");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                columnNames.add(resultSet.getString("column_name"));
            }
            return columnNames;
        } catch (SQLException e) {
            throw new SQLException();
        }

    }

}
