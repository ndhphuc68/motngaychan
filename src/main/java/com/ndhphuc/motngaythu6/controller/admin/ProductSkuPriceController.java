package com.ndhphuc.motngaythu6.controller.admin;

import com.ndhphuc.motngaythu6.dto.ApiResponse;
import com.ndhphuc.motngaythu6.dto.CreateProductSkuPriceDTO;
import com.ndhphuc.motngaythu6.service.ProductSkuPriceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "ProductSkuPrice Admin Controller")
@RestController
@RequestMapping(value = "/api/v1/admin/productSkuPrice")
@CrossOrigin
@PreAuthorize("hasRole('ADMIN')")
public class ProductSkuPriceController {

  @Autowired
  ProductSkuPriceService productSkuPriceService;

  @PostMapping(value = "/create")
  public ApiResponse createSkuPrice(@RequestBody CreateProductSkuPriceDTO productSkuPriceDTO) {
    ApiResponse apiResponse = new ApiResponse();
    try {
      CreateProductSkuPriceDTO skuPriceDTO = productSkuPriceService.createProductSkuPrice(productSkuPriceDTO);
      apiResponse.setData(skuPriceDTO);
      apiResponse.setSuccess(skuPriceDTO != null);
    } catch (Exception e) {
      apiResponse.setMessage(e.getMessage());
      apiResponse.setSuccess(false);
    }
    return apiResponse;
  }
}
