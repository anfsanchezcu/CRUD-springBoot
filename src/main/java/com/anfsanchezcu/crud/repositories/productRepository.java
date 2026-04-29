package com.anfsanchezcu.crud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.anfsanchezcu.crud.entities.Product;

public interface productRepository extends CrudRepository<Product, Long> {
  
}
