package com.dev.spring.security.jwt.controller;

import com.dev.spring.security.jwt.entity.User;
import com.dev.spring.security.jwt.model.UserRequest;
import com.dev.spring.security.jwt.model.UserResponse;
import com.dev.spring.security.jwt.service.UserServiceImplementation;
import com.dev.spring.security.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserServiceImplementation userService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/saveUser")
    public String saveUser(@RequestBody User user) {
        Integer id = userService.saveUser(user);
        String message= "User with id '"+id+"' saved succssfully!";
        return message;
    }

    @RequestMapping(path = "/signIn")
    public UserResponse login(@RequestBody UserRequest userRequest){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userRequest.getUsername(),userRequest.getPassword());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        String token=jwtUtil.generateToken(userRequest.getUsername());
        return new UserResponse(token,"token generated successfully");
    }

    @GetMapping("/getAllUser")
    public List<User> getAllUser() {
        return userService.findAll();
    }
}
