package com.dinosdev.udemy.OrdersService.command;

import com.dinosdev.udemy.OrdersService.core.model.OrderStatus;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CreateOrderCommand {
  @TargetAggregateIdentifier
  String id;
  String userId = "27b95829-4f3f-4ddf-8983-151ba010e35b";
  String productId;
  String addressId;
  int quantity;
  OrderStatus orderStatus = OrderStatus.CREATED;
}
