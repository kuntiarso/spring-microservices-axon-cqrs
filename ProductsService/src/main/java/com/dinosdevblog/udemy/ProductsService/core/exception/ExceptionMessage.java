package com.dinosdevblog.udemy.ProductsService.core.exception;

import java.util.Date;
import lombok.Value;

@Value
public class ExceptionMessage {
  Date timestamp;
  String message;
}
