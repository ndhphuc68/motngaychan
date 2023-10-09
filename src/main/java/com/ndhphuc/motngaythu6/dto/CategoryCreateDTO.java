package com.ndhphuc.motngaythu6.dto;

import lombok.Getter;

@Getter
public class CategoryCreateDTO {
  private Integer parentCategoryId;

  private String nameCategory;

  private String description;

  public void setParentCategoryId(Integer parentCategoryId) {
    this.parentCategoryId = parentCategoryId;
  }

  public void setNameCategory(String nameCategory) {
    this.nameCategory = nameCategory;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
