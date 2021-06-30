package com.easyspring.service;

import com.easyspring.entity.User;

import java.util.List;

public interface UserService {

    public String addUser(User user) throws Exception;
    public List<User> getAllUser();
    public String loginDetailToken(User user) throws Exception;
}
