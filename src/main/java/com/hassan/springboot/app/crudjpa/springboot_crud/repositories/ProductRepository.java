package com.hassan.springboot.app.crudjpa.springboot_crud.repositories;


import org.springframework.data.repository.CrudRepository;

import com.hassan.springboot.app.crudjpa.springboot_crud.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

}
