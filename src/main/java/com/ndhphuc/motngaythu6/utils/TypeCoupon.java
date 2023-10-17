package com.ndhphuc.motngaythu6.utils;

import lombok.Getter;

@Getter
public enum TypeCoupon {
  MONEY("MONEY"),
  PERCENTAGE_OF_INVOICES("PERCENTAGE_OF_INVOICES");

  private String type;

  TypeCoupon(String action) {
    this.type = action;
  }
}
