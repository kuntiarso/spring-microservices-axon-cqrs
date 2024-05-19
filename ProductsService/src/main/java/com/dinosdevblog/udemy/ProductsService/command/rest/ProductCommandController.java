package com.dinosdevblog.udemy.ProductsService.command.rest;

import com.dinosdevblog.udemy.ProductsService.command.mapper.ProductCommandMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "products", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class ProductCommandController {
  private final CommandGateway commandGateway;
  private final ProductCommandMapper productCommandMapper;

  @PostMapping
  public String createProduct(@Valid @RequestBody CreateProductRestModel createProductRestModel) {
    try {
      log.info("[PRINT] inside controller of create product -> {}", createProductRestModel);
      return commandGateway.sendAndWait(productCommandMapper.mapToCreateProductCommand(createProductRestModel));
    } catch (Exception e) {
      return e.getLocalizedMessage();
    }
  }
}
