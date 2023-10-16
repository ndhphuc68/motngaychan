package com.ndhphuc.motngaythu6.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Getter
@Entity
@Table(name = "product_category")
public class ProductCategory {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id;

  private Integer categoryId;

  private String productCode;

  private Date careateDate;

  public void setId(Integer id) {
    this.id = id;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  public void setCareateDate(Date careateDate) {
    this.careateDate = careateDate;
  }
}
