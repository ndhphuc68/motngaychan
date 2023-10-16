package com.ndhphuc.motngaythu6.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreateProductSkuPriceDTO {
  private String productCode;

  private String nameSale;

  private BigDecimal price;

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  public void setNameSale(String nameSale) {
    this.nameSale = nameSale;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }
}
