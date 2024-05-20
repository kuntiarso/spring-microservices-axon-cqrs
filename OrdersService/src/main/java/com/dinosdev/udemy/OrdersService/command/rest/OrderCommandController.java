package com.dinosdev.udemy.OrdersService.command.rest;

import com.dinosdev.udemy.OrdersService.command.mapper.OrderCommandMapper;
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
@RequestMapping(value = "orders", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class OrderCommandController {
  private final CommandGateway commandGateway;
  private final OrderCommandMapper orderCommandMapper;

  @PostMapping
  public String createOrder(@Valid @RequestBody CreateOrderRestModel createOrderRestModel) {
    log.info("[ENTRY] inside controller of create order -> {}", createOrderRestModel);
    return commandGateway.sendAndWait(orderCommandMapper.mapToCreateOrderCommand(createOrderRestModel));
  }
}
