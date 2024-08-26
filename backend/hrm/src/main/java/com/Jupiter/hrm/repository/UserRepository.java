package com.Jupiter.hrm.repository;
import com.Jupiter.hrm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM user", nativeQuery = true)
    List<User> findAllUsers();


    @Modifying
    @Query(value = "INSERT INTO user (username, password, employee_id) VALUES (:username, :password, :employeeId)", nativeQuery = true)
    void saveUser(@Param("username") String username, @Param("password") String password, @Param("employeeId") String employeeId);


    @Query(value = "SELECT * FROM user WHERE username = :username AND password = :password", nativeQuery = true)
    User findByUsernameAndPassword(String username, String password);

    @Query(value = "SELECT * FROM user WHERE user_id = :userID", nativeQuery = true)
    Optional<User> findById(Long userID);


    void delete(User user);
}























