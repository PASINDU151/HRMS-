package com.Jupiter.hrm.service;
import com.Jupiter.hrm.entity.User;
import com.Jupiter.hrm.repository.UserRepo;
import com.Jupiter.hrm.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Jupiter.hrm.repository.LeaveTypeRepository;
import com.Jupiter.hrm.security.PasswordHasher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.sql.*;
import java.util.List;
import java.util.Optional;
@Service
public class UserService {

    private final UserRepository userRepository;
    private ModelMapper modelMapper;
    private UserRepo userRepo;
    private BCryptPasswordEncoder encoder;

    @Autowired
    UserService(UserRepository userRepository, ModelMapper modelMapper, UserRepo userRepo, BCryptPasswordEncoder encoder){
        this.userRepository = userRepository;
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.modelMapper = modelMapper;
    }

    public void createUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));
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
        User user = userRepo.findByUsername(username);

        if (user == null) {
            // User with the given username does not exist
            return null;
        }


        // Verify the entered password using PasswordHasher.verifyPassword
        if (encoder.matches(password, user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }
}
