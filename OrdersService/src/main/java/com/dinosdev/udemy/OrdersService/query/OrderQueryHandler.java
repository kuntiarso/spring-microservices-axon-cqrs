package com.dinosdev.udemy.OrdersService.query;

import com.dinosdev.udemy.OrdersService.core.data.OrderRepository;
import com.dinosdev.udemy.OrdersService.query.mapper.OrderQueryMapper;
import com.dinosdev.udemy.OrdersService.query.rest.OrderRestModel;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderQueryHandler {
  private final OrderRepository orderRepository;
  private final OrderQueryMapper orderQueryMapper;

  @QueryHandler
  public List<OrderRestModel> findOrders(FindOrderQuery findOrderQuery) {
    List<OrderRestModel> orders = orderQueryMapper.mapToOrderRestModelList(orderRepository.findAll());
    log.info("[SUCCESS] inside query handler of find orders");
    return orders;
  }
}
