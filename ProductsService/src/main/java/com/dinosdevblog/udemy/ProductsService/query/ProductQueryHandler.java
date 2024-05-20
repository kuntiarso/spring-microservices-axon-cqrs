package com.dinosdevblog.udemy.ProductsService.query;

import com.dinosdevblog.udemy.ProductsService.core.data.ProductRepository;
import com.dinosdevblog.udemy.ProductsService.query.mapper.ProductQueryMapper;
import com.dinosdevblog.udemy.ProductsService.query.rest.ProductRestModel;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductQueryHandler {
  private final ProductRepository productRepository;
  private final ProductQueryMapper productQueryMapper;

  @QueryHandler
  public List<ProductRestModel> findProducts(FindProductQuery findProductQuery) {
    List<ProductRestModel> products = productQueryMapper.mapToProductRestModelList(productRepository.findAll());
    log.info("[SUCCESS] inside query handler of find products");
    return products;
  }
}
