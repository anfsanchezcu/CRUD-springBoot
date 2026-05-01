package com.anfsanchezcu.crud.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anfsanchezcu.crud.ProductValidation;
import com.anfsanchezcu.crud.entities.Product;
import com.anfsanchezcu.crud.services.productService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class productController {

  @Autowired
  private ProductValidation validation;

  @Autowired
  private productService service;

  @GetMapping
  public List<Product> findAll() {
    return service.finAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> view(@PathVariable Long id) {
    Optional<Product> productOptional = service.findById(id);
    if (productOptional.isPresent()) {
      return ResponseEntity.ok(productOptional.orElseThrow());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result) {
   
    //validation.validate(product, result);
    if(result.hasFieldErrors()) {
      return validation(result);
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult result, @PathVariable Long id){
    if(result.hasFieldErrors()) {
      return validation(result);
    }

    Optional<Product> productOptional = service.update(id,product);
    if(productOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<Product> productOptional = service.findById(id);
    if (productOptional.isPresent()) {
      return ResponseEntity.ok(productOptional.orElseThrow());
    }
    return ResponseEntity.notFound().build();
  }

  private ResponseEntity<?> validation(BindingResult result) {
    Map<String, String> errors = new HashMap<>();
    
    result.getFieldErrors().forEach(error ->{
      errors.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
    });

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }



}
