package com.dinosdevblog.udemy.ProductsService.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CreateProductCommand {
  @TargetAggregateIdentifier
  String id;
  String name;
  String description;
}
