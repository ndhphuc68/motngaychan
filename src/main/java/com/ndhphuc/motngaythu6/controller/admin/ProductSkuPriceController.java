package com.ndhphuc.motngaythu6.controller.admin;

import com.ndhphuc.motngaythu6.dto.ApiResponse;
import com.ndhphuc.motngaythu6.dto.CreateProductSkuPriceDTO;
import com.ndhphuc.motngaythu6.service.ProductSkuPriceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
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

  @PostMapping(value = "/update")
  public ApiResponse updateSkuPrice(@RequestBody CreateProductSkuPriceDTO productSkuPriceDTO) {
    ApiResponse apiResponse = new ApiResponse();
    try {
      CreateProductSkuPriceDTO skuPriceDTO = productSkuPriceService.updateProductSkuPrice(productSkuPriceDTO);
      apiResponse.setData(skuPriceDTO);
      apiResponse.setSuccess(skuPriceDTO != null);
    } catch (Exception e) {
      apiResponse.setMessage(e.getMessage());
      apiResponse.setSuccess(false);
    }
    return apiResponse;
  }

  @GetMapping(value = "/list")
  public ApiResponse getListSkuPrice(@RequestParam(required = false) String searchText, @RequestParam(required = false) Integer isSale, @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) {
    ApiResponse apiResponse = new ApiResponse();
    try {
      apiResponse.setData(productSkuPriceService.getListProductSkuPrice(isSale, searchText, startDate, endDate));
      apiResponse.setSuccess(true);
    } catch (Exception e) {
      apiResponse.setMessage(e.getMessage());
      apiResponse.setSuccess(false);
    }
    return apiResponse;
  }

  @GetMapping(value = "/action")
  public ApiResponse actionProduct(@RequestParam Integer productSkuCode, @RequestParam String type) {
    ApiResponse apiResponse = new ApiResponse();
    try {
      if (productSkuCode == null || !StringUtils.hasText(type)) {
        apiResponse.setSuccess(false);
      }
      apiResponse.setSuccess(productSkuPriceService.actionProductSku(productSkuCode, type));
      apiResponse.setMessage("Success");
    } catch (Exception e) {
      apiResponse.setSuccess(false);
      apiResponse.setMessage(e.getMessage());
    }
    return apiResponse;
  }
}
