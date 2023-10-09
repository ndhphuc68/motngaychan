package com.ndhphuc.motngaythu6.service;

import com.ndhphuc.motngaythu6.dto.CategoryCreateDTO;
import com.ndhphuc.motngaythu6.dto.CategoryDTO;
import com.ndhphuc.motngaythu6.model.Category;
import com.ndhphuc.motngaythu6.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CategoryService {

  @Autowired
  CategoryRepository categoryRepository;

  public CategoryCreateDTO createCategory(CategoryCreateDTO createDTO) {
    Category category = new Category();
    category.setNameCategory(createDTO.getNameCategory());
    category.setCreateDate(new Date());
    category.setDescription(createDTO.getDescription());
    category.setIsBlock(0);
    category.setParentCategoryId(createDTO.getParentCategoryId() != null ? createDTO.getParentCategoryId() : null);
    categoryRepository.save(category);
    return createDTO;
  }

  public List<CategoryDTO> getListCategory(Integer id) {
    List<CategoryDTO> list = new ArrayList<>();
    CategoryDTO categoryDTO = null;
    if (id == null) {
      List<Category> categories = categoryRepository.findCategoryParentCategoryIdNull();
      for (Category category : categories) {
         categoryDTO = new CategoryDTO();
        categoryDTO = conventCategory(category);
        categoryDTO.setChildren(getListCategory(category.getId()));
        list.add(categoryDTO);
      }
    } else {
      List<Category> categories = categoryRepository.findCategoryByParentCategoryId(id);
      for (Category category : categories) {
        categoryDTO = new CategoryDTO();
        categoryDTO = conventCategory(category);
        categoryDTO.setChildren(getListCategory(category.getId()));
        list.add(categoryDTO);
      }
    }
    return list;
  }

  public CategoryDTO conventCategory(Category category) {
    CategoryDTO categoryDTO = new CategoryDTO();
    categoryDTO.setNameCategory(category.getNameCategory());
    categoryDTO.setCreateDate(category.getCreateDate());
    categoryDTO.setId(category.getId());
    categoryDTO.setParentCategoryId(category.getParentCategoryId());
    categoryDTO.setDescription(category.getDescription());
    categoryDTO.setIsBlock(category.getIsBlock());
    return categoryDTO;
  }
}
