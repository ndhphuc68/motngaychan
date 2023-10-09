package com.ndhphuc.motngaythu6.model;


import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Entity
@Table(name = "categorys")
@Getter
public class Category {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private Integer parentCategoryId;

  private String nameCategory;

  private String description;

  private Date createDate;

  private Integer isBlock;

  public void setIsBlock(Integer isBlock) {
    this.isBlock = isBlock;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setParentCategoryId(Integer parentCategoryId) {
    this.parentCategoryId = parentCategoryId;
  }

  public void setNameCategory(String nameCategory) {
    this.nameCategory = nameCategory;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }
}
