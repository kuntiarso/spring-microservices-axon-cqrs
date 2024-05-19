package com.dinosdevblog.udemy.ProductsService.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class CreateProductCommand {
  @TargetAggregateIdentifier
  String id;
  String name;
  String description;
}
