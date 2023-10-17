package com.ndhphuc.motngaythu6.utils;

import lombok.Getter;

@Getter
public enum ActionUser {
  BLOCK("BLOCK"),
  DELETE("DELETE");

  private String action;

  ActionUser(String action) {
    this.action = action;
  }

}
