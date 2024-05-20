package com.dinosdev.udemy.OrdersService.command;

import com.dinosdev.udemy.OrdersService.core.model.OrderStatus;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CreateOrderCommand {
  @TargetAggregateIdentifier
  String id;
  String userId;
  String productId;
  String addressId;
  int quantity;
  OrderStatus orderStatus;
}
