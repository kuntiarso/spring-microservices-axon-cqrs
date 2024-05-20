package com.dinosdevblog.udemy.ProductsService.core.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class ProductEntity implements Serializable {
  @Serial
  private static final long serialVersionUID = -227264951080660124L;

  @Id @Column(unique = true, nullable = false)
  private String id;
  @Column(unique = true, nullable = false)
  private String name;
  private String description;
}
