package com.dinosdev.udemy.CoreService.command;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@SuperBuilder
public class ReserveProductCommand {
  @TargetAggregateIdentifier
  String productId;
  String orderId;
  String userId;
  int quantity;
}
