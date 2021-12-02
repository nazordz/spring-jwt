package com.crud.service;

import java.util.List;

import com.crud.entity.Role;
import com.crud.entity.User;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addUserToRole(String email, String roleName);
    User getUser(String email);
    List<User> getUsers();

}

