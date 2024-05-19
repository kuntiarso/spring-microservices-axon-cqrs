package com.dinosdevblog.udemy.ProductsService.query.rest;

import com.dinosdevblog.udemy.ProductsService.query.FindProductQuery;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "products", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductQueryController {
  private final QueryGateway queryGateway;

  @GetMapping
  public List<ProductRestModel> getProducts() {
    FindProductQuery findProductQuery = new FindProductQuery();
    return queryGateway.query(findProductQuery, ResponseTypes.multipleInstancesOf(ProductRestModel.class)).join();
  }
}
