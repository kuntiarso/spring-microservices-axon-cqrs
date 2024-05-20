package com.dinosdev.udemy.OrdersService.command;

import com.dinosdev.udemy.OrdersService.command.mapper.OrderCommandMapper;
import com.dinosdev.udemy.OrdersService.core.event.OrderCreatedEvent;
import com.dinosdev.udemy.OrdersService.core.model.OrderStatus;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
@Slf4j
public class OrderAggregate {
  @AggregateIdentifier
  private String id;
  private String userId;
  private String productId;
  private String addressId;
  private int quantity;
  private OrderStatus orderStatus;

  @CommandHandler
  public OrderAggregate(CreateOrderCommand createOrderCommand, OrderCommandMapper orderCommandMapper) {
    OrderCreatedEvent orderCreatedEvent = orderCommandMapper.mapToOrderCreatedEvent(createOrderCommand);
    log.info("[PRINT] inside command handler of create order -> {}", orderCreatedEvent);
    AggregateLifecycle.apply(orderCreatedEvent);
  }

  @EventSourcingHandler
  public void on(OrderCreatedEvent orderCreatedEvent) {
    this.id = orderCreatedEvent.getId();
    this.userId = orderCreatedEvent.getUserId();
    this.productId = orderCreatedEvent.getProductId();
    this.addressId = orderCreatedEvent.getAddressId();
    this.quantity = orderCreatedEvent.getQuantity();
    this.orderStatus = orderCreatedEvent.getOrderStatus();
  }
}
