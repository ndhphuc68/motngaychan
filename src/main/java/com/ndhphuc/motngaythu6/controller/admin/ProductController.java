package com.ndhphuc.motngaythu6.controller.admin;

import com.ndhphuc.motngaythu6.dto.ProductDTO;
import com.ndhphuc.motngaythu6.dto.ApiResponse;
import com.ndhphuc.motngaythu6.model.Product;
import com.ndhphuc.motngaythu6.service.ProductService;
import io.swagger.models.auth.In;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Tag(name = "Product Admin Controller")
@RestController
@RequestMapping(value = "/api/v1/admin/product")
@CrossOrigin
@PreAuthorize("hasRole('ADMIN')")
public class ProductController {

  @Autowired
  ProductService productService;

  @GetMapping(value = "/list")
  public ApiResponse getListProduct(@RequestParam(required = false) String searchText, @RequestParam(required = false) Integer isBlock) {
    ApiResponse apiResponse = new ApiResponse();
    try {
      apiResponse.setData(productService.getListProduct(isBlock, searchText, new Date(), new Date()));
      apiResponse.setSuccess(true);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return apiResponse;
  }

  @PostMapping(value = "/create")
  public ApiResponse createProduct(@RequestBody ProductDTO productDTO) {
    ApiResponse apiResponse = new ApiResponse();
    try {
      if (productDTO != null) {
        Product product = productService.createProduct(productDTO);
        apiResponse.setData(product);
        apiResponse.setSuccess(true);
      } else {
        apiResponse.setSuccess(false);
      }

    } catch (Exception e) {
      apiResponse.setMessage(e.getMessage());
      apiResponse.setSuccess(false);
    }
    return apiResponse;
  }
}
