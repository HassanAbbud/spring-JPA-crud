package com.hassan.springboot.app.crudjpa.springboot_crud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.hassan.springboot.app.crudjpa.springboot_crud.entities.Person;

public interface PersonRepository extends CrudRepository<Person,Long>{
    

}
