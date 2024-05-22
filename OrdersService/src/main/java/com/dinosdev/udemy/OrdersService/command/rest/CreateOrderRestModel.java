package com.dinosdev.udemy.OrdersService.command.rest;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@SuperBuilder
public class CreateOrderRestModel {
  @NotBlank(message = "product id cannot be empty")
  String productId;
  @NotBlank(message = "address id cannot be empty")
  String addressId;
  @Min(value = 1, message = "order quantity cannot be lower than 1")
  Integer quantity;
}
