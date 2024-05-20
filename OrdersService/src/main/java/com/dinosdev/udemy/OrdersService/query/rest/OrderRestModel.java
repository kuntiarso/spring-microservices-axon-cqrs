package com.dinosdev.udemy.OrdersService.query.rest;

import com.dinosdev.udemy.OrdersService.core.model.OrderStatus;
import lombok.Value;

@Value
public class OrderRestModel {
  String id;
  String userId;
  String productId;
  String addressId;
  Integer quantity;
  OrderStatus orderStatus;
}
