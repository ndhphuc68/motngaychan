package com.ndhphuc.motngaythu6.utils;

import lombok.Getter;

@Getter
public enum StatusCoupon {
  WRITE("WRITE"),STOP("STOP"), PAUSE("PAUSE"), ACTIVATED("ACTIVATED");

  private String status;

  StatusCoupon(String action) {
    this.status = action;
  }
}
