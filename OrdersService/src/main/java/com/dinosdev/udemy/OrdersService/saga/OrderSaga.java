package com.dinosdev.udemy.OrdersService.saga;

import com.dinosdev.udemy.CoreService.command.ReserveProductCommand;
import com.dinosdev.udemy.CoreService.event.ProductReservedEvent;
import com.dinosdev.udemy.OrdersService.core.event.OrderCreatedEvent;
import javax.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.CommandResultMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

@Saga
@RequiredArgsConstructor
@Slf4j
public class OrderSaga {
  private final transient CommandGateway commandGateway;

  @StartSaga
  @SagaEventHandler(associationProperty = "id")
  public void handle(OrderCreatedEvent orderCreatedEvent) {
    ReserveProductCommand reserveProductCommand = ReserveProductCommand.builder()
        .orderId(orderCreatedEvent.getId())
        .userId(orderCreatedEvent.getUserId())
        .productId(orderCreatedEvent.getProductId())
        .quantity(orderCreatedEvent.getQuantity())
        .build();

    log.info("[PRINT] inside saga order created event -> {}", reserveProductCommand);
    commandGateway.send(reserveProductCommand, new CommandCallback<ReserveProductCommand, Object>() {
      @Override
      public void onResult(@Nonnull CommandMessage<? extends ReserveProductCommand> commandMessage,
          @Nonnull CommandResultMessage<?> commandResultMessage) {
        if (commandResultMessage.isExceptional()) {
          // compensate command
        }
      }
    });
  }

  @SagaEventHandler(associationProperty = "orderId")
  public void handle(ProductReservedEvent productReservedEvent) {
    // process user payment
    log.info("[PRINT] inside saga product reserved event -> {}", productReservedEvent);
  }

}
