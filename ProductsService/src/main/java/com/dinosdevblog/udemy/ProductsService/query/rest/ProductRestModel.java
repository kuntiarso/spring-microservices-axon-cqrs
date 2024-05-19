package com.dinosdevblog.udemy.ProductsService.query.rest;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
public class ProductRestModel {
  String id;
  String name;
  String description;
}
