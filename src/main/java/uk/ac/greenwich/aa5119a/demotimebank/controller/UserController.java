package uk.ac.greenwich.aa5119a.demotimebank.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import uk.ac.greenwich.aa5119a.demotimebank.model.Rating;
import uk.ac.greenwich.aa5119a.demotimebank.service.UserService;
import uk.ac.greenwich.aa5119a.demotimebank.model.User;
import uk.ac.greenwich.aa5119a.demotimebank.web.LoginResponse;
import uk.ac.greenwich.aa5119a.demotimebank.web.RegisterResponse;

@RestController
public class UserController {

    @Autowired
    UserService userService;

//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private MyUserDetailsService myUserDetailsService;
//
//    @Autowired
//    private JwtUtil jwtTokenUtil;


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
    public void rateUser(@PathVariable("userId") int userId, @PathVariable("rating") int rating){
         userService.rateUser(userId, rating);
    }


//    @PostMapping("/authenticate")
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
//
//        try{
//
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
//        );
//        } catch (BadCredentialsException e){
//            throw new Exception("Incorrect email or password", e);
//        }
//
//        final UserDetails userDetails = myUserDetailsService
//                .loadUserByUsername(authenticationRequest.getEmail());
//
//
//        final String jwt = jwtTokenUtil.generateToken(userDetails);
//
//        return ResponseEntity.ok(new AuthenticationResponse(jwt));
//    }
}
