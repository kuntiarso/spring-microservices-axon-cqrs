package com.dinosdevblog.udemy.ProductsService;

import com.dinosdevblog.udemy.ProductsService.command.interceptor.CreateProductCommandInterceptor;
import com.dinosdevblog.udemy.ProductsService.core.exception.ProductServiceEventsExceptionHandler;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductsServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(ProductsServiceApplication.class, args);
  }

  @Autowired
  public void registerCreateProductCommandInterceptor(ApplicationContext context, CommandBus commandBus) {
    commandBus.registerDispatchInterceptor(context.getBean(CreateProductCommandInterceptor.class));
  }

  @Autowired
  public void configureListenerErrorHandler(EventProcessingConfigurer configurer) {
    configurer.registerListenerInvocationErrorHandler("product-events-group", configuration -> new ProductServiceEventsExceptionHandler());
  }
}
