package com.ndhphuc.motngaythu6.model;


import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "product_sku_price")
@Getter
public class ProductSkuPrice {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id;

  private String productCode;

  private String nameSale;

  private Integer isSale;

  private Date createDate;

  private Date updateDate;

  private BigDecimal price;

  private Integer isDelete;

  public void setIsDelete(Integer isDelete) {
    this.isDelete = isDelete;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public void setIsSale(Integer isSale) {
    this.isSale = isSale;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  public void setNameSale(String nameSale) {
    this.nameSale = nameSale;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }
}
