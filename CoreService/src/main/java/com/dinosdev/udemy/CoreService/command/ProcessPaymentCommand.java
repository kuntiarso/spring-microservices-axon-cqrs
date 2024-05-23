package com.dinosdev.udemy.CoreService.command;

import com.dinosdev.udemy.CoreService.core.model.PaymentDetails;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@SuperBuilder
public class ProcessPaymentCommand {
  String paymentId;
  String orderId;
  PaymentDetails paymentDetails;
}
