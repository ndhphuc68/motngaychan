package com.ndhphuc.motngaythu6.utils;

public enum ActionUser {
  BLOCK("BLOCK"),
  DELETE("DELETE");

  private String action;

  ActionUser(String action) {
    this.action = action;
  }

  public String getAction() {
    return action;
  }
}
