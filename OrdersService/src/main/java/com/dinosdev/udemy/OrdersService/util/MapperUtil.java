package com.dinosdev.udemy.OrdersService.util;

import java.util.UUID;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MapperUtil {
  public final String GENERATED_UUID = "java(com.dinosdev.udemy.OrdersService.util.MapperUtil.generateUuid())";

  public String generateUuid() {
    return UUID.randomUUID().toString();
  }
}
