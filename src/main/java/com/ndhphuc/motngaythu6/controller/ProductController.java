package com.ndhphuc.motngaythu6.controller;

import com.ndhphuc.motngaythu6.dto.ProductDTO;
import com.ndhphuc.motngaythu6.model.ApiResponse;
import com.ndhphuc.motngaythu6.model.Product;
import com.ndhphuc.motngaythu6.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.annotations.OpenAPI31;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Product Controller")
@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(value = "/list")
    public ApiResponse getListProduct() {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(productService.getListProduct());
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
            throw new RuntimeException(e);
        }
        return apiResponse;
    }
}
