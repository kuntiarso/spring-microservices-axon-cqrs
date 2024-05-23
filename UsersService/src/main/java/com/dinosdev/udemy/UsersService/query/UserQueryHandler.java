package com.dinosdev.udemy.UsersService.query;

import com.dinosdev.udemy.CoreService.core.model.PaymentDetails;
import com.dinosdev.udemy.CoreService.core.model.User;
import com.dinosdev.udemy.CoreService.core.query.FetchUserPaymentDetailsQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserQueryHandler {
  @QueryHandler
  public User findUser(FetchUserPaymentDetailsQuery query) {
    log.info("[ENTRY] inside query handler find user");
    return User.builder()
        .id(query.getUserId())
        .firstName("Dinos")
        .lastName("Developer")
        .paymentDetails(PaymentDetails.builder()
            .cardNumber("5555555555")
            .cvv("123")
            .name("Dinos Developer")
            .validUntilMonth(12)
            .validUntilYear(2030)
            .build())
        .build();
  }
}
