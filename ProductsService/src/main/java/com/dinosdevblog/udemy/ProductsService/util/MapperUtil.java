package com.dinosdevblog.udemy.ProductsService.util;

import java.util.UUID;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MapperUtil {
  public final String GENERATE_UUID = "java(com.dinosdevblog.udemy.ProductsService.util.MapperUtil.generateUuid())";

  public String generateUuid() {
    return UUID.randomUUID().toString();
  }
}
