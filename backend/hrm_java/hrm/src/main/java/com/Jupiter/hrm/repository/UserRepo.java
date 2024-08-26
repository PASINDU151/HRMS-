package com.Jupiter.hrm.repository;

import com.Jupiter.hrm.entity.User;
import com.Jupiter.hrm.config.DbConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepo {
    Connection connection;
    public UserRepo() {
        try {
            connection = DbConfig.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void save(User user){
        try{
            String sqlQuery = "INSERT INTO user (username, password, employee_id, salt) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmployee_id());
            preparedStatement.setString(4, user.getSalt());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> findAllUsers() {
        List<User> userList = new ArrayList<>();

        try {
            String sqlQuery = "SELECT * FROM user";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userList.add(getUser(new User(), resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public User findByUsername(String username) {
        User user = null;

        try {
            String sqlQuery = "SELECT * FROM user WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = getUser(new User(), resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    public User findByUsernameAndPassword(String username, String password) {
        User user = null;

        try {
            String sqlQuery = "SELECT * FROM user WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = getUser(new User(), resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    public Optional<User> findById(Long userID){
        User user = null;

        try {
            String sqlQuery = "SELECT * FROM user WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setLong(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = getUser(new User(), resultSet);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.ofNullable(user);
    }

    public void delete(User user){
        try {
            String sqlQuery = "DELETE FROM user WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setLong(1, user.getUser_id());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private User getUser(User user, ResultSet resultSet){
        try {
            user.setUser_id(resultSet.getLong("user_id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setEmployee_id(resultSet.getString("employee_id"));
            user.setSalt(resultSet.getString("salt"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public void update(User user) {
        try {
            String sqlQuery = "UPDATE user SET username = ?, password = ? where user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getUser_id());
            preparedStatement.executeUpdate();
            int rowsUpdated = preparedStatement.executeUpdate();

            System.out.println(rowsUpdated);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
