package com.hassan.springboot.app.crudjpa.springboot_crud.services;

import java.util.List;

import com.hassan.springboot.app.crudjpa.springboot_crud.entities.User;

public interface UserService {
    List<User> findAll();

    User save(User user);
}
