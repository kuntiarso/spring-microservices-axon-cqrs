package com.dinosdevblog.udemy.ProductsService.command.interceptor;

import com.dinosdevblog.udemy.ProductsService.command.CreateProductCommand;
import com.dinosdevblog.udemy.ProductsService.core.data.ProductLookupEntity;
import com.dinosdevblog.udemy.ProductsService.core.data.ProductLookupRepository;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import javax.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {
  private final ProductLookupRepository productLookupRepository;

  @Nonnull
  @Override
  public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends CommandMessage<?>> messages) {
    return (index, command) -> {
      log.info("[ENTRY] inside interceptor of {}", command.getPayloadType());
      if (CreateProductCommand.class.equals(command.getPayloadType())) {
        CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();
        log.info("[PRINT] casting command into -> {}", createProductCommand);
        ProductLookupEntity productLookupEntity = productLookupRepository.findByIdOrName(createProductCommand.getId(), createProductCommand.getName());
        log.info("[PRINT] looking up potential duplicate of products");
        if (Objects.nonNull(productLookupEntity)) {
          log.info("[VALIDATION] product already exists with id {} or name {}", productLookupEntity.getId(), productLookupEntity.getName());
          throw new IllegalStateException("Similar product is already registered");
        }
      }
      return command;
    };
  }
}
