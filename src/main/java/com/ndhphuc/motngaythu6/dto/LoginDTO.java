package com.ndhphuc.motngaythu6.dto;

import lombok.Getter;

@Getter
public class LoginDTO {
  private String username;

  private String password;

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
