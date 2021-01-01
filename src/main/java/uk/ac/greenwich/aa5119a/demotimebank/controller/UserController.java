package uk.ac.greenwich.aa5119a.demotimebank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.greenwich.aa5119a.demotimebank.service.UserService;
import uk.ac.greenwich.aa5119a.demotimebank.model.User;
import uk.ac.greenwich.aa5119a.demotimebank.web.LoginResponse;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody User user) {
        return userService.login(user);
    }

    @PutMapping("/password")
    public User changePassword(@RequestBody User user) {
        return userService.changePassword(user);
    }

    @PutMapping("/logout")
    public User logout(@RequestBody User user) {
        return userService.logout(user);
    }


}
