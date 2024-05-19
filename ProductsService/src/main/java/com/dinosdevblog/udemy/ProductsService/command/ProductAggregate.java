package com.dinosdevblog.udemy.ProductsService.command;

import com.dinosdevblog.udemy.ProductsService.command.mapper.ProductCommandMapper;
import com.dinosdevblog.udemy.ProductsService.core.events.ProductCreatedEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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

  @CommandHandler
  public ProductAggregate(CreateProductCommand createProductCommand, ProductCommandMapper productCommandMapper) {
    if (StringUtils.isEmpty(createProductCommand.getName())) {
      throw new IllegalArgumentException("[VALIDATION] product's name cannot be empty");
    }
    ProductCreatedEvent productCreatedEvent = productCommandMapper.mapToProductCreatedEvent(createProductCommand);
    log.info("[PRINT] inside command handler of create product -> {}", productCreatedEvent);
    AggregateLifecycle.apply(productCreatedEvent);
  }

  @EventSourcingHandler
  public void on(ProductCreatedEvent productCreatedEvent) {
    this.id = productCreatedEvent.getId();
    this.name = productCreatedEvent.getName();
    this.description = productCreatedEvent.getDescription();
  }
}
