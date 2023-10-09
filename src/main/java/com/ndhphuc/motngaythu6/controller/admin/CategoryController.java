package com.ndhphuc.motngaythu6.controller.admin;


import com.ndhphuc.motngaythu6.dto.ApiResponse;
import com.ndhphuc.motngaythu6.dto.CategoryCreateDTO;
import com.ndhphuc.motngaythu6.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Category Admin Controller")
@RestController
@RequestMapping(value = "/api/v1/admin/category")
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('ADMIN')")
public class CategoryController {

  @Autowired
  CategoryService categoryService;

  @PostMapping(value = "/create")
  public ApiResponse createCategory(@RequestBody CategoryCreateDTO createDTO) {
    ApiResponse apiResponse = new ApiResponse();
    try {
      if(createDTO == null){
        apiResponse.setMessage("No value");
        apiResponse.setSuccess(false);
        return apiResponse;
      }
      apiResponse.setData(categoryService.createCategory(createDTO));
      apiResponse.setSuccess(true);
      apiResponse.setMessage("Create Success");
    } catch (Exception e) {
      apiResponse.setMessage(e.getMessage());
      apiResponse.setSuccess(false);
    }
    return apiResponse;
  }

  @GetMapping(value = "/list")
  public ApiResponse getListCategory() {
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
