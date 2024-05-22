package com.dinosdevblog.udemy.ProductsService.query.rest;

import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
public class ProductRestModel {
  String id;
  String name;
  String description;
  int quantity;
  BigDecimal price;
}
