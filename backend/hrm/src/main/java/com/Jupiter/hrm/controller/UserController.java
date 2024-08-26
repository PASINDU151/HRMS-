package com.Jupiter.hrm.controller;
import com.Jupiter.hrm.entity.User;
import com.Jupiter.hrm.dto.UserDto;
import com.Jupiter.hrm.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Type;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping
    public HttpStatus createUser(@RequestBody UserDto userDto) {
        userService.createUser(modelMapper.map(userDto, User.class));
        return HttpStatus.CREATED;
    }


    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        Type listType = new TypeToken<List<UserDto>>() {}.getType();
        List<UserDto> users = modelMapper.map(userService.getAllUsers(), listType);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = modelMapper.map(userService.getUserById(id), UserDto.class);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }


    @PutMapping("/{id}")
    public HttpStatus updateUser(@PathVariable Long id, @RequestBody UserDto updatedUser) {
        if(userService.updateUser(id, modelMapper.map(updatedUser, User.class))){
            return HttpStatus.OK;
        }
        else{
            return HttpStatus.NOT_FOUND;
        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if(deleted){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDto userDto) {
        User user = userService.findByUsernameAndPassword(userDto.getUsername(), userDto.getPassword());

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }

}

