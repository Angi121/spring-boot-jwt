package com.easyspring.service.impl;

import com.easyspring.Repository.UserRepository;
import com.easyspring.entity.Role;
import com.easyspring.entity.User;
import com.easyspring.security.JWTTokenProvider;
import com.easyspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private JWTTokenProvider provier;

    @Autowired
    private AuthenticationManager auth;

    @Override
    public String addUser(User user) throws Exception {
        if(repository.findByName(user.getUserName()) == null){
            if(CollectionUtils.isEmpty(user.getRoles())){
                List<Role> roles = new ArrayList<>();
                roles.add(Role.ROLE_ADMIN);
                user.setRoles(roles);
            }
            user.setActive(true);
            repository.saveAndFlush(user);
            return provier.createToken(user.getUserName(), repository.findByName(user.getUserName()).getRoles());
        }else{
            throw new Exception("User already exsist by username");
        }
    }

    @Override
    public List<User> getAllUser() {
        return repository.findAll();
    }

    @Override
    public String loginDetailToken(User user) throws Exception {
        try{
            auth.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            return provier.createToken(user.getUserName(), repository.findByName(user.getUserName()).getRoles());
        }catch(Exception e){
            throw new Exception("Invalid user name or Password");
        }
    }
}
