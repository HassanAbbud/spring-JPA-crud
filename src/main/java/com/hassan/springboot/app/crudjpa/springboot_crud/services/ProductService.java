package com.hassan.springboot.app.crudjpa.springboot_crud.services;

import java.util.List;
import java.util.Optional;

import com.hassan.springboot.app.crudjpa.springboot_crud.entities.Product;

public interface ProductService {
    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product Save(Product product);
    Optional<Product> update(Long id, Product product);

    Optional<Product> delete(Long id);


}
