package com.dinosdevblog.udemy.ProductsService.command.rest;

import jakarta.validation.constraints.NotBlank;
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
}
