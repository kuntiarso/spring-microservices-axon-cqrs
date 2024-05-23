package com.dinosdev.udemy.CoreService.core.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@SuperBuilder
public class User {
  String id;
  String firstName;
  String lastName;
  PaymentDetails paymentDetails;
}
