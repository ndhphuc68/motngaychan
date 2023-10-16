package com.ndhphuc.motngaythu6.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ListCategoryDTO {

  private CategoryDTO data;

  private List<ListCategoryDTO> children;

  public void setData(CategoryDTO data) {
    this.data = data;
  }

  public void setChildren(List<ListCategoryDTO> children) {
    this.children = children;
  }
}
