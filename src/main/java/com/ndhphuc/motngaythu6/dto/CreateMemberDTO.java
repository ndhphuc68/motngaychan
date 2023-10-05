package com.ndhphuc.motngaythu6.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class CreateMemberDTO {
  private String username;

  private String password;

  private String name;

  private String email;

  private String phone;

  private Date createDate;

  private int isBlock;

  public void setIsBlock(int isBlock) {
    this.isBlock = isBlock;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }
}
