package com.dinosdevblog.udemy.ProductsService.core.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
  ProductEntity findByIdOrName(String id, String name);
}
