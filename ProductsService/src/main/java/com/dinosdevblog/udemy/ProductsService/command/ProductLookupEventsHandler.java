package com.dinosdevblog.udemy.ProductsService.command;

import com.dinosdevblog.udemy.ProductsService.command.mapper.ProductCommandMapper;
import com.dinosdevblog.udemy.ProductsService.core.data.ProductEntity;
import com.dinosdevblog.udemy.ProductsService.core.data.ProductLookupEntity;
import com.dinosdevblog.udemy.ProductsService.core.data.ProductLookupRepository;
import com.dinosdevblog.udemy.ProductsService.core.events.ProductCreatedEvent;
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
public class ProductLookupEventsHandler {
  private final ProductLookupRepository productLookupRepository;
  private final ProductCommandMapper productCommandMapper;

  @EventHandler
  public void on(ProductCreatedEvent productCreatedEvent) {
    ProductLookupEntity productLookupEntity = productCommandMapper.mapToProductLookupEntity(productCreatedEvent);
    log.info("[PRINT] inside event handler of create lookup product -> {}", productLookupEntity);
    productLookupRepository.save(productLookupEntity);
  }

  @ExceptionHandler
  public void handleException(Exception exception) throws Exception {
    log.error("[FAILURE] inside product lookup events handler -> {}", exception.getMessage());
    throw exception;
  }
}
