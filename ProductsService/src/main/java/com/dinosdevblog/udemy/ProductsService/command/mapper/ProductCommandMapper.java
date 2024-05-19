package com.dinosdevblog.udemy.ProductsService.command.mapper;

import com.dinosdevblog.udemy.ProductsService.command.CreateProductCommand;
import com.dinosdevblog.udemy.ProductsService.command.rest.CreateProductRestModel;
import com.dinosdevblog.udemy.ProductsService.core.data.ProductEntity;
import com.dinosdevblog.udemy.ProductsService.core.data.ProductLookupEntity;
import com.dinosdevblog.udemy.ProductsService.core.events.ProductCreatedEvent;
import com.dinosdevblog.udemy.ProductsService.util.MapperUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface ProductCommandMapper {
  @Mapping(target = "id", expression = MapperUtil.GENERATE_UUID)
  CreateProductCommand mapToCreateProductCommand(CreateProductRestModel createProductRestModel);
  ProductCreatedEvent mapToProductCreatedEvent(CreateProductCommand createProductCommand);
  ProductEntity mapToProductEntity(ProductCreatedEvent productCreatedEvent);
  ProductLookupEntity mapToProductLookupEntity(ProductCreatedEvent productCreatedEvent);
}
