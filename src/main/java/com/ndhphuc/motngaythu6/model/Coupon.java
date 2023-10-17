package com.ndhphuc.motngaythu6.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "coupons")
@Getter
public class Coupon {

  @Id
  @Column(name = "coupon_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer couponId;

  private String couponName;

  private String description;

  private Integer isDelete = 0;

  private String status;

  private BigDecimal totalInvoice;

  private BigDecimal reducedValue;

  private String typeCoupon;

  private Date startDate;

  private Date endDate;

  private Date createDate;

  private Date updateDate;

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public void setCouponId(Integer couponId) {
    this.couponId = couponId;
  }

  public void setCouponName(String couponName) {
    this.couponName = couponName;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setIsDelete(Integer isDelete) {
    this.isDelete = isDelete;
  }

  public void setStatus(String status) {
    this.status = status;
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

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }
}
