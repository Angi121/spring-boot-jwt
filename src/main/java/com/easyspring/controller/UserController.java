package com.easyspring.controller;

import com.easyspring.entity.User;
import com.easyspring.security.JWTTokenProvider;
import com.easyspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/signup")
    public String addUser(@RequestBody User user) throws Exception {
        return service.addUser(user);
    }

    @PostMapping("/login")
    public String loginToken(@RequestBody User user) throws Exception{
        return service.loginDetailToken(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public List<User> getAllUser(){
      return service.getAllUser();
    }
}
