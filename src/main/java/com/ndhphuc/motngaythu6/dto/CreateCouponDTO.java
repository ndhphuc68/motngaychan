package com.ndhphuc.motngaythu6.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Getter
public class CreateCouponDTO {

  public Integer couponId;

  private String couponName;

  private String description;

  private BigDecimal totalInvoice;

  private BigDecimal reducedValue;

  private String typeCoupon;

  private Long startDate;

  private Long endDate;

  public void setCouponName(String couponName) {
    this.couponName = couponName;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setTotalInvoice(BigDecimal totalInvoice) {
    this.totalInvoice = totalInvoice;
  }

  public void setReducedValue(BigDecimal reducedValue) {
    this.reducedValue = reducedValue;
  }

  public void setTypeCoupon(String typeCoupon) {
    this.typeCoupon = typeCoupon;
  }

  public void setStartDate(Long startDate) {
    this.startDate = startDate;
  }

  public void setEndDate(Long endDate) {
    this.endDate = endDate;
  }
}
