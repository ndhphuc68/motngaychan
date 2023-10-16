package com.ndhphuc.motngaythu6.dto;

import lombok.Getter;

@Getter
public class UploadDTO {

  private String name;

  private String url;

  private String type;

  public void setType(String type) {
    this.type = type;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
