package com.Jupiter.hrm.service;
import com.Jupiter.hrm.entity.User;
import com.Jupiter.hrm.repository.UserRepo;
import com.Jupiter.hrm.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Jupiter.hrm.repository.LeaveTypeRepository;

import java.sql.*;
import java.util.List;
import java.util.Optional;
@Service
public class UserService {


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
    private final UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository){this.userRepository = userRepository;}

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private LeaveTypeRepository leaveTypeRepository;

    public void createUser(User user){
        userRepo.save(user);
    }

    public List<User> getAllUsers(){
        return  userRepo.findAllUsers();
    }

    public User getUserById(Long id){
        Optional<User> optionalUser = userRepo.findById(id);
        return optionalUser.orElse(null);
    }

    public boolean updateUser(Long id, User updatedUser) {
        Optional<User> optionalUser = userRepo.findById(id);
        updatedUser.setUser_id(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            System.out.println(updatedUser.getUser_id()+user.getUsername()+updatedUser.getPassword());
            userRepo.update(updatedUser);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean deleteUser(Long id){
        Optional<User> optionalUser = userRepo.findById(id);
        if(optionalUser.isPresent()){
            userRepo.delete(optionalUser.get());
            return true;
        }
        else{
            return false;
        }
    }

    public User findByUsernameAndPassword(String username, String password) {
        User user = userRepo.findByUsernameAndPassword(username, password);
        if(user == null){
            return null;
        }
        else{
            return user;
        }
    }
}
