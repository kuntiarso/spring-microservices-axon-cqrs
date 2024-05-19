package com.dinosdevblog.udemy.ProductsService.query.mapper;

import com.dinosdevblog.udemy.ProductsService.core.data.ProductEntity;
import com.dinosdevblog.udemy.ProductsService.query.rest.ProductRestModel;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface ProductQueryMapper {
  List<ProductRestModel> mapToProductRestModelList(List<ProductEntity> productEntities);
}
