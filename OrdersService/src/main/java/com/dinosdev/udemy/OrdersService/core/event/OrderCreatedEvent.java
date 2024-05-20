package com.dinosdev.udemy.OrdersService.core.event;

import com.dinosdev.udemy.OrdersService.core.model.OrderStatus;
import lombok.Data;

@Data
public class OrderCreatedEvent {
  private String id;
  private String userId;
  private String productId;
  private String addressId;
  private int quantity;
  private OrderStatus orderStatus;
}
