package com.secureroute.SecureRoute.controller;

import com.secureroute.SecureRoute.models.User;
import com.secureroute.SecureRoute.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UserController {

    @Autowired
    private  UserService userService;

    @GetMapping("/read")
    public List<User> readUser() {
        return userService.readUser();
    }

    @GetMapping("/read/{userId}")
    public User readUserById(@PathVariable Long userId) {
        return userService.readUserById(userId);
    }

    @GetMapping("/read/children/{parentId}")
    public List<User> readUsersByParentId(@PathVariable Long parentId) {
        return userService.readUsersByParentId(parentId);
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User updateUser){
        return userService.updateUser(updateUser);
    }
}
