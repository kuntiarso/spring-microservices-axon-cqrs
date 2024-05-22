package com.dinosdevblog.udemy.ProductsService.core.events;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductCreatedEvent {
  private String id;
  private String name;
  private String description;
  private int quantity;
  private BigDecimal price;
}
