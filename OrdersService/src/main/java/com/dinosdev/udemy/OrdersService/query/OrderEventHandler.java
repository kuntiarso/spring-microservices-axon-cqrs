package com.dinosdev.udemy.OrdersService.query;

import com.dinosdev.udemy.OrdersService.command.mapper.OrderCommandMapper;
import com.dinosdev.udemy.OrdersService.core.data.OrderEntity;
import com.dinosdev.udemy.OrdersService.core.data.OrderRepository;
import com.dinosdev.udemy.OrdersService.core.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventHandler {
  private final OrderRepository orderRepository;
  private final OrderCommandMapper orderCommandMapper;

  @EventHandler
  public void on(OrderCreatedEvent orderCreatedEvent) {
    OrderEntity orderEntity = orderCommandMapper.mapToOrderEntity(orderCreatedEvent);
    log.info("[PRINT] inside event handler of create order -> {}", orderEntity);
    orderRepository.save(orderEntity);
  }

  @ExceptionHandler
  public void handleException(Exception exception) throws Exception {
    log.error("[FAILURE] inside order events handler -> {}", exception.getMessage());
    throw exception;
  }
}
