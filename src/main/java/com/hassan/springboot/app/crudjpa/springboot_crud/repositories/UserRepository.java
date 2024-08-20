package com.hassan.springboot.app.crudjpa.springboot_crud.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import com.hassan.springboot.app.crudjpa.springboot_crud.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{

    boolean existsByUsername(String username);


    Optional<User> findByUsername(String username);
}
