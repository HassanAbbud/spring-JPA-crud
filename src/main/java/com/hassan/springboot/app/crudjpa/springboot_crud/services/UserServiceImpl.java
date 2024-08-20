package com.hassan.springboot.app.crudjpa.springboot_crud.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hassan.springboot.app.crudjpa.springboot_crud.entities.Role;
import com.hassan.springboot.app.crudjpa.springboot_crud.entities.User;
import com.hassan.springboot.app.crudjpa.springboot_crud.repositories.RoleRepository;
import com.hassan.springboot.app.crudjpa.springboot_crud.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired 
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {
        
        Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_USER");
        List<Role> roles =  new ArrayList<>();
        
        optionalRoleUser.ifPresent(roles::add);

        if (user.isAdmin()) {
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }

        user.setRoles(roles);
        String passwordEncoded =  passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoded);
        return userRepository.save(user);
    }

}
