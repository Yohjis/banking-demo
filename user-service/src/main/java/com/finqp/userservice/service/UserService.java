package com.finqp.userservice.service;

import com.finqp.userservice.entity.User;
import com.finqp.userservice.entity.UserRole;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User register(User user);

    void updateUser(long id, String name, String password, UserRole userType) throws IllegalArgumentException;
    void deleteUser(long id);


    User findByUsername(String username);
    User findById(Long id)throws IllegalArgumentException;;


}