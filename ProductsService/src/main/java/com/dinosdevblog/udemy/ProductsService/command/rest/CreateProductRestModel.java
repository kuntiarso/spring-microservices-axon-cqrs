package com.dinosdevblog.udemy.ProductsService.command.rest;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@SuperBuilder
public class CreateProductRestModel {
  @NotBlank(message = "product name cannot be empty")
  String name;
  @NotBlank(message = "product description cannot be empty")
  String description;
  @NotNull
  @Min(value = 1, message = "product quantity cannot be lower than 1")
  Integer quantity;
  @NotNull
  @DecimalMin(value = "1.0", message = "product price cannot be lower than 1")
  BigDecimal price;
}
