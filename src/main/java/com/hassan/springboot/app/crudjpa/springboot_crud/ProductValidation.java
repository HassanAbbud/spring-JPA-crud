package com.hassan.springboot.app.crudjpa.springboot_crud;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hassan.springboot.app.crudjpa.springboot_crud.entities.Product;

@Component
public class ProductValidation implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null, "can't be empty");

        if (product.getDescription() == null || product.getDescription().isBlank()) {
            errors.rejectValue("description", null , "is required");
        }
        if (product.getPrice() == null) {
            errors.rejectValue("price", null, "can't be null");
        } else if (product.getPrice() < 500) {
            errors.rejectValue("price", null, "must be greater than or equal to 500");
        }
    }

}
