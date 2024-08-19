package com.hassan.springboot.app.crudjpa.springboot_crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hassan.springboot.app.crudjpa.springboot_crud.entities.Product;
import com.hassan.springboot.app.crudjpa.springboot_crud.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    
    }

    @Override
    @Transactional
    public Optional<Product> update(Long id, Product product) {
        
        Optional<Product> optionalProduct = productRepository.findById(id); 
        if (optionalProduct.isPresent()) {
            Product productDb = optionalProduct.orElseThrow();

            productDb.setName(product.getName());
            productDb.setDescription(productDb.getDescription());
            productDb.setPrice(productDb.getPrice());

            return Optional.of(productRepository.save(productDb));

        };

        return optionalProduct;
    }

    @Override
    @Transactional
    public Optional<Product> delete(Long id) {
        Optional<Product> productOptional = productRepository.findById(id); 
        productOptional.ifPresent(productDb -> {
            productRepository.delete(productDb);
        });

        return productOptional;
    }


}
