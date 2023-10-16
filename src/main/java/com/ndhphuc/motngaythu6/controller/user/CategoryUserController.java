package com.ndhphuc.motngaythu6.controller.user;


import com.ndhphuc.motngaythu6.dto.ApiResponse;
import com.ndhphuc.motngaythu6.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Category Controller")
@RestController
@RequestMapping(value = "/api/v1/category")
@CrossOrigin(origins = "*")
public class CategoryUserController {

  @Autowired
  CategoryService categoryService;

  @GetMapping(value = "/list")
  public ApiResponse getListCategoryUser() {
    ApiResponse apiResponse = new ApiResponse();
    try {
      apiResponse.setData(categoryService.getListCategory(null));
      apiResponse.setSuccess(true);
    } catch (Exception e) {
      apiResponse.setMessage(e.getMessage());
      apiResponse.setSuccess(false);
    }
    return apiResponse;
  }
}
