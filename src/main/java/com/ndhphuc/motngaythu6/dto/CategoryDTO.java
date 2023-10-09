package com.ndhphuc.motngaythu6.dto;

import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class CategoryDTO {
  private Integer id;

  private Integer parentCategoryId;

  private String nameCategory;

  private String description;

  private Date createDate;

  private Integer isBlock;

  public void setIsBlock(Integer isBlock) {
    this.isBlock = isBlock;
  }

  private List<CategoryDTO> children;

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

  public void setChildren(List<CategoryDTO> children) {
    this.children = children;
  }
}
