package com.dinosdev.udemy.CoreService.core.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@SuperBuilder
public class PaymentDetails {
  String name;
  String cardNumber;
  int validUntilMonth;
  int validUntilYear;
  String cvv;
}
