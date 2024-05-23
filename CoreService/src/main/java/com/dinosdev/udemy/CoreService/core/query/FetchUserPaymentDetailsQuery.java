package com.dinosdev.udemy.CoreService.core.query;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class FetchUserPaymentDetailsQuery {
  private String userId;
}
