package com.ndhphuc.motngaythu6.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Entity
@Table(name = "uploads")
@Getter
public class Upload {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id;

  private String nameImage;

  @Column(name = "image", unique = false, nullable = false, length = 100000)
  private byte[] image;

  private String type;

  private Date createDate;

  public void setId(Integer id) {
    this.id = id;
  }

  public void setNameImage(String nameImage) {
    this.nameImage = nameImage;
  }

  public void setImage(byte[] image) {
    this.image = image;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }
}
