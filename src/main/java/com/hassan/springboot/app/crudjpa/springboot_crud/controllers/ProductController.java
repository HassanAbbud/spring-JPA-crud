package com.hassan.springboot.app.crudjpa.springboot_crud.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hassan.springboot.app.crudjpa.springboot_crud.ProductValidation;
import com.hassan.springboot.app.crudjpa.springboot_crud.entities.Product;
import com.hassan.springboot.app.crudjpa.springboot_crud.services.ProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    //Custom validator of errors
    @Autowired
    private ProductValidation validation;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<Product> list(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> findProduct(@PathVariable Long id) {
        Optional<Product> optionalProduct = productService.findById(id);
        if (optionalProduct.isPresent()) {
            return ResponseEntity.ok(optionalProduct.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    
    //Post method with validation parameters being checked
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result) {
        //custom validation using validator class
        validation.validate(product, result);
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));        
    }
    
    //Put method with validation parameters being checked
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult result, @PathVariable Long id) {
        //custom validation using validator class
        validation.validate(product, result);
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Product> optionalProduct = productService.update(id, product);
        if (optionalProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalProduct.orElseThrow());        
        }
        return ResponseEntity.notFound().build();
    }

    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        
        Optional<Product> optionalProduct = productService.delete(id);
        if (optionalProduct.isPresent()) {
            return ResponseEntity.ok(optionalProduct.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors =  new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "The field " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
    
}
