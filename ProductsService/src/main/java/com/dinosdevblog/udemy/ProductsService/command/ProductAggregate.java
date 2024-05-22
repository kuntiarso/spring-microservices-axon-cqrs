package com.dinosdevblog.udemy.ProductsService.command;

import com.dinosdev.udemy.CoreService.command.ReserveProductCommand;
import com.dinosdev.udemy.CoreService.event.ProductReservedEvent;
import com.dinosdevblog.udemy.ProductsService.command.mapper.ProductCommandMapper;
import com.dinosdevblog.udemy.ProductsService.core.events.ProductCreatedEvent;
import java.math.BigDecimal;
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
public class ProductAggregate {
  @AggregateIdentifier
  private String id;
  private String name;
  private String description;
  private int quantity;
  private BigDecimal price;

  @CommandHandler
  public ProductAggregate(CreateProductCommand createProductCommand, ProductCommandMapper productCommandMapper) {
    ProductCreatedEvent productCreatedEvent = productCommandMapper.mapToProductCreatedEvent(createProductCommand);
    log.info("[PRINT] inside command handler of create product -> {}", productCreatedEvent);
    AggregateLifecycle.apply(productCreatedEvent);
  }

  @CommandHandler
  public ProductAggregate(ReserveProductCommand reserveProductCommand, ProductCommandMapper productCommandMapper) {
    if (quantity < reserveProductCommand.getQuantity()) {
      throw new IllegalArgumentException("Items are unavailable or out of stock");
    }
    ProductReservedEvent productReservedEvent = productCommandMapper.mapToProductReservedEvent(reserveProductCommand);
    log.info("[PRINT] inside command handler of reserve product -> {}", productReservedEvent);
    AggregateLifecycle.apply(productReservedEvent);
  }

  @EventSourcingHandler
  public void on(ProductCreatedEvent productCreatedEvent) {
    this.id = productCreatedEvent.getId();
    this.name = productCreatedEvent.getName();
    this.description = productCreatedEvent.getDescription();
    this.quantity = productCreatedEvent.getQuantity();
    this.price = productCreatedEvent.getPrice();
  }

  @EventSourcingHandler
  public void on(ProductReservedEvent productReservedEvent) {
    this.quantity -= productReservedEvent.getQuantity();
  }
}
