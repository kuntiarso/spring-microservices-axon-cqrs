package com.dinosdev.udemy.CoreService.event;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@SuperBuilder
public class ProductReservedEvent {
  String productId;
  String orderId;
  String userId;
  int quantity;
}
