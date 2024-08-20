package com.hassan.springboot.app.crudjpa.springboot_crud.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hassan.springboot.app.crudjpa.springboot_crud.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
    Optional<Role> findByName(String name);
}
