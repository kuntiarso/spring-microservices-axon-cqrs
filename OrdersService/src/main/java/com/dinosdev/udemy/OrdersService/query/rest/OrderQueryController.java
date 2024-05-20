package com.dinosdev.udemy.OrdersService.query.rest;

import com.dinosdev.udemy.OrdersService.query.FindOrderQuery;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "orders", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OrderQueryController {
  private final QueryGateway queryGateway;

  @GetMapping
  public List<OrderRestModel> getOrders() {
    FindOrderQuery findOrderQuery = new FindOrderQuery();
    return queryGateway.query(findOrderQuery, ResponseTypes.multipleInstancesOf(OrderRestModel.class)).join();
  }
}
