package com.anfsanchezcu.crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anfsanchezcu.crud.entities.Product;
import com.anfsanchezcu.crud.repositories.productRepository;

@Service
public class productServiceImpl implements productService {
  @Autowired
  private productRepository repository;

  @Override
  @Transactional
  public void delete(Product product) {
    Optional<Product> productDB = repository.findById(product.getId());
    productDB.ifPresent(prod -> repository.delete(prod));
  }

  @Override
  @Transactional 
  public void deleteById(Long id) {
    
  }

  @Override
  @Transactional(readOnly = true)
  public List<Product> finAll() {
    return (List<Product>)repository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Product> findById(Long id) {
    return repository.findById(id);
  }

  @Override
  @Transactional
  public Product save(Product product) {
    return repository.save(product);
  }
  
}
