package com.dinosdevblog.udemy.ProductsService.core.events;

import lombok.Data;

@Data
public class ProductCreatedEvent {
  private String id;
  private String name;
  private String description;
}
