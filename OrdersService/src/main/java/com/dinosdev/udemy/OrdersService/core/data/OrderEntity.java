package com.dinosdev.udemy.OrdersService.core.data;

import com.dinosdev.udemy.OrdersService.core.model.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class OrderEntity {
  private static final long serialVersionUID = -3218765828621165577L;

  @Id @Column(unique = true, nullable = false)
  private String id;
  @Column(nullable = false)
  private String userId;
  @Column(nullable = false)
  private String productId;
  @Column(nullable = false)
  private String addressId;
  @Column(nullable = false)
  private int quantity;
  @Enumerated(EnumType.STRING) @Column(nullable = false)
  private OrderStatus orderStatus;
}
