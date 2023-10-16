package com.ndhphuc.motngaythu6.service;

import com.ndhphuc.motngaythu6.dto.CategoryCreateDTO;
import com.ndhphuc.motngaythu6.dto.CategoryDTO;
import com.ndhphuc.motngaythu6.dto.ListCategoryDTO;
import com.ndhphuc.motngaythu6.model.Category;
import com.ndhphuc.motngaythu6.repository.CategoryRepository;
import com.ndhphuc.motngaythu6.utils.ActionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CategoryService {

  @Autowired
  CategoryRepository categoryRepository;

  public boolean actionCategory(Integer categoryId, String type) {
    Category category = categoryRepository.findCategoryById(categoryId);
    if (category != null) {
      if (ActionUser.DELETE.getAction().equals(type)) {
        categoryRepository.delete(category);
        return true;
      } else if (ActionUser.BLOCK.getAction().equals(type)) {
        if (category.getIsBlock() == 1) {
          category.setIsBlock(0);
        } else {
          category.setIsBlock(1);
          List<Category> categories = categoryRepository.findCategoryByParentCategoryId(categoryId);
          if (!categories.isEmpty()) {
            for (Category value : categories) {
              value.setIsBlock(1);
              categoryRepository.save(value);
            }
          }
        }
        categoryRepository.save(category);
        return true;
      }
    }
    return false;
  }

  public CategoryCreateDTO createCategory(CategoryCreateDTO createDTO) {
    if (createDTO.getId() != null) {
      Category category = categoryRepository.findCategoryById(createDTO.getId());
      if (category != null) {
        category.setDescription(createDTO.getDescription());
        category.setNameCategory(createDTO.getNameCategory());
        category.setParentCategoryId(createDTO.getParentCategoryId());
        categoryRepository.save(category);
        return createDTO;
      }
    }
    Category category = new Category();
    category.setNameCategory(createDTO.getNameCategory());
    category.setCreateDate(new Date());
    category.setDescription(createDTO.getDescription());
    category.setIsBlock(0);
    category.setParentCategoryId(createDTO.getParentCategoryId() != null ? createDTO.getParentCategoryId() : null);
    categoryRepository.save(category);
    return createDTO;
  }

  public List<ListCategoryDTO> getListCategory(Integer id) {
    List<ListCategoryDTO> list = new ArrayList<>();
    ListCategoryDTO listCategoryDTO = null;
    if (id == null) {
      List<Category> categories = categoryRepository.findCategoryParentCategoryIdNull();
      for (Category category : categories) {
        listCategoryDTO = new ListCategoryDTO();
        listCategoryDTO.setData(conventCategory(category));
        listCategoryDTO.setChildren(getListCategory(category.getId()));
        list.add(listCategoryDTO);
      }
    } else {
      List<Category> categories = categoryRepository.findCategoryByParentCategoryId(id);
      for (Category category : categories) {
        listCategoryDTO = new ListCategoryDTO();
        listCategoryDTO.setData(conventCategory(category));
        listCategoryDTO.setChildren(getListCategory(category.getId()));
        list.add(listCategoryDTO);
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
