package com.anfsanchezcu.crud.services;

import java.util.List;
import java.util.Optional;

import com.anfsanchezcu.crud.entities.Product;

public interface productService {

  List<Product> finAll();
  Optional<Product> findById(Long id);
  Product save(Product product);
  void deleteById(Long id);
  void delete (Product product);

  
}
