package com.jwt.security.service;

import com.jwt.security.domain.Role;
import com.jwt.security.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String userName, String roleName);
    User getUser(String username);
    List<User> getUsers();
}
