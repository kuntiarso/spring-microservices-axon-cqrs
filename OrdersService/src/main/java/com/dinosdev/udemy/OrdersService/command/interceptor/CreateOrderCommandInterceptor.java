package com.dinosdev.udemy.OrdersService.command.interceptor;

import com.dinosdev.udemy.OrdersService.command.CreateOrderCommand;
import java.util.List;
import java.util.function.BiFunction;
import javax.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreateOrderCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {
  @Nonnull
  @Override
  public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends CommandMessage<?>> messages) {
    return (index, command) -> {
      log.info("[ENTRY] inside interceptor of {}", command.getPayloadType());
      if (CreateOrderCommand.class.equals(command.getPayloadType())) {
        CreateOrderCommand createOrderCommand = (CreateOrderCommand) command.getPayload();
        log.info("[PRINT] casting command into -> {}", createOrderCommand);
        if (createOrderCommand.getQuantity() > 1000) {
          log.info("[VALIDATION] order quantity is too much -> {} items", createOrderCommand.getQuantity());
          throw new IllegalStateException("Huge order detected");
        }
      }
      return command;
    };
  }
}
