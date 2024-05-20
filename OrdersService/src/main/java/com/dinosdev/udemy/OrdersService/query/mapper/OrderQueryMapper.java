package com.dinosdev.udemy.OrdersService.query.mapper;

import com.dinosdev.udemy.OrdersService.core.data.OrderEntity;
import com.dinosdev.udemy.OrdersService.query.rest.OrderRestModel;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface OrderQueryMapper {
  List<OrderRestModel> mapToOrderRestModelList(List<OrderEntity> orderEntities);
}
