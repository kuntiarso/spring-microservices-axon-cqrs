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
@Table(name = "products_lookup")
public class ProductLookupEntity implements Serializable {
  @Serial
  private static final long serialVersionUID = -7018098202410367331L;

  @Id @Column(unique = true, nullable = false)
  private String id;
  @Column(unique = true, nullable = false)
  private String name;
}
