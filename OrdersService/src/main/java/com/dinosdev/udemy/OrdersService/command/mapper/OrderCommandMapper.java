package com.dinosdev.udemy.OrdersService.command.mapper;

import com.dinosdev.udemy.OrdersService.command.CreateOrderCommand;
import com.dinosdev.udemy.OrdersService.command.rest.CreateOrderRestModel;
import com.dinosdev.udemy.OrdersService.core.data.OrderEntity;
import com.dinosdev.udemy.OrdersService.core.event.OrderCreatedEvent;
import com.dinosdev.udemy.OrdersService.util.MapperUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface OrderCommandMapper {
  @Mapping(target = "id", expression = MapperUtil.GENERATED_UUID)
  CreateOrderCommand mapToCreateOrderCommand(CreateOrderRestModel createOrderRestModel);
  OrderCreatedEvent mapToOrderCreatedEvent(CreateOrderCommand createOrderCommand);
  OrderEntity mapToOrderEntity(OrderCreatedEvent orderCreatedEvent);
}
