package com.hassan.springboot.app.crudjpa.springboot_crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.hassan.springboot.app.crudjpa.springboot_crud.entities.User;
import com.hassan.springboot.app.crudjpa.springboot_crud.services.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<User> list(@RequestParam String param) {
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }


    public ResponseEntity<?> validation(BindingResult result ){
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "The field" + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
    
}
