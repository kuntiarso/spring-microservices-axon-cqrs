package com.dinosdevblog.udemy.ProductsService.core.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductLookupRepository extends JpaRepository<ProductLookupEntity, String> {
  ProductLookupEntity findByIdOrName(String id, String name);
}
