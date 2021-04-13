package uk.ac.greenwich.aa5119a.demotimebank.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.*;
import uk.ac.greenwich.aa5119a.demotimebank.model.response.LoginResponse;
import uk.ac.greenwich.aa5119a.demotimebank.model.response.RegisterResponse;
import uk.ac.greenwich.aa5119a.demotimebank.service.RatingService;
import uk.ac.greenwich.aa5119a.demotimebank.service.UserService;
import uk.ac.greenwich.aa5119a.demotimebank.model.User;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RatingService ratingService;



    @PostMapping("/register")
    public RegisterResponse register(@RequestBody User user) {
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

    @PostMapping("/rate/{userId}/{rating}")
    public void rateUser(@PathVariable("userId") int userId, @PathVariable("rating") int rating, String comments){
         userService.rateUser(userId, rating, comments);
    }

    @PostMapping("user/rate")
    public Boolean rateUser(@RequestParam int userId, @RequestParam float rating, @RequestParam String comments){
        return ratingService.rateUser(userId, rating, comments);
    }


}
