package com.dinosdev.udemy.OrdersService.saga;

import com.dinosdev.udemy.CoreService.command.ProcessPaymentCommand;
import com.dinosdev.udemy.CoreService.command.ReserveProductCommand;
import com.dinosdev.udemy.CoreService.core.model.User;
import com.dinosdev.udemy.CoreService.core.query.FetchUserPaymentDetailsQuery;
import com.dinosdev.udemy.CoreService.event.ProductReservedEvent;
import com.dinosdev.udemy.OrdersService.core.event.OrderCreatedEvent;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.CommandResultMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;

@Saga
@RequiredArgsConstructor
@Slf4j
public class OrderSaga {
  private final transient CommandGateway commandGateway;
  private final transient QueryGateway queryGateway;

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
    log.info("[PRINT] inside saga product reserved event -> {}", productReservedEvent);
    User user = null;
    try {
      FetchUserPaymentDetailsQuery fetchUserPaymentDetailsQuery = FetchUserPaymentDetailsQuery.builder()
          .userId(productReservedEvent.getUserId())
          .build();
      user = queryGateway.query(fetchUserPaymentDetailsQuery, ResponseTypes.instanceOf(User.class)).join();
    } catch (Exception e) {
      log.error("[FAILURE] fetch user query failed -> {}", e.getMessage());
      // compensating transaction
      return;
    }

    if (Objects.isNull(user) || Objects.isNull(user.getPaymentDetails())) {
      log.error("[NULL] query respond with null user");
      // compensating transaction
      return;
    }

    log.info("[PRINT] successfully fetched user -> {}", user);
    String result = null;
    try {
      ProcessPaymentCommand processPaymentCommand = ProcessPaymentCommand.builder()
          .paymentId(UUID.randomUUID().toString())
          .orderId(productReservedEvent.getOrderId())
          .paymentDetails(user.getPaymentDetails())
          .build();
      result = commandGateway.sendAndWait(processPaymentCommand, 10, TimeUnit.SECONDS);
    } catch (Exception e) {
      log.error("[FAILURE] process payment command failed -> {}", e.getMessage());
      // compensating transaction
    }
  }

}
