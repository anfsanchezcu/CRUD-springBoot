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
  public void delete(Long id) {
    Optional<Product> productDB = repository.findById(id);
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


  @Override
  @Transactional
  public Optional<Product> update(Long id,Product product) {
    Optional<Product> productOptional = repository.findById(id);
    if(productOptional.isPresent()) {
      Product productDB = productOptional.orElseThrow();

      productDB.setName(product.getName());
      productDB.setPrice(product.getPrice());
      productDB.setDescription(product.getDescription());
      return Optional.of(repository.save(productDB));
    };
    
    return productOptional;
  }
  
}
