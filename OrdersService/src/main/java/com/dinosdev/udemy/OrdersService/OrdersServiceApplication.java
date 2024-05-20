package com.dinosdev.udemy.OrdersService;

import com.dinosdev.udemy.OrdersService.command.interceptor.CreateOrderCommandInterceptor;
import com.dinosdev.udemy.OrdersService.core.exception.OrderServiceEventsExceptionHandler;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
public class OrdersServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(OrdersServiceApplication.class, args);
	}

	@Autowired
	public void registerCreateOrderCommandInterceptor(ApplicationContext context, CommandBus commandBus) {
		commandBus.registerDispatchInterceptor(context.getBean(CreateOrderCommandInterceptor.class));
	}

	@Autowired
	public void configureListenerErrorHandler(EventProcessingConfigurer configurer) {
		configurer.registerListenerInvocationErrorHandler("order-events-group", configuration -> new OrderServiceEventsExceptionHandler());
	}
}
