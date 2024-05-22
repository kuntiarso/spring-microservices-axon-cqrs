package com.dinosdevblog.udemy.ProductsService.query;

import com.dinosdev.udemy.CoreService.event.ProductReservedEvent;
import com.dinosdevblog.udemy.ProductsService.command.mapper.ProductCommandMapper;
import com.dinosdevblog.udemy.ProductsService.core.data.ProductEntity;
import com.dinosdevblog.udemy.ProductsService.core.data.ProductRepository;
import com.dinosdevblog.udemy.ProductsService.core.events.ProductCreatedEvent;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup(value = "product-events-group")
@RequiredArgsConstructor
@Slf4j
public class ProductEventHandler {
  private final ProductRepository productRepository;
  private final ProductCommandMapper productCommandMapper;

  @EventHandler
  public void on(ProductCreatedEvent productCreatedEvent) {
    ProductEntity productEntity = productCommandMapper.mapToProductEntity(productCreatedEvent);
    log.info("[PRINT] handle create product -> {}", productEntity);
    productRepository.save(productEntity);
  }

  @EventHandler
  public void on(ProductReservedEvent productReservedEvent) {
    ProductEntity productEntity = productRepository.findById(productReservedEvent.getProductId()).orElseThrow();
    productEntity.setQuantity(productEntity.getQuantity() - productReservedEvent.getQuantity());
    log.info("[PRINT] handle update product stock to {}", productEntity.getQuantity());
    productRepository.save(productEntity);
  }

  @ExceptionHandler
  public void handleException(Exception exception) throws Exception {
    log.error("[FAILURE] inside product events handler -> {}", exception.getMessage());
    throw exception;
  }
}
