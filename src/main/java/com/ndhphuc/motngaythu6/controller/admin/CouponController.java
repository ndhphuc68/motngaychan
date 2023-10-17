package com.ndhphuc.motngaythu6.controller.admin;

import com.ndhphuc.motngaythu6.dto.ApiResponse;
import com.ndhphuc.motngaythu6.dto.CreateCouponDTO;
import com.ndhphuc.motngaythu6.model.Product;
import com.ndhphuc.motngaythu6.service.CouponService;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Coupon Admin Controller")
@RestController
@RequestMapping(value = "/api/v1/admin/coupon")
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('ADMIN')")
public class CouponController {

  @Autowired
  CouponService couponService;


  @PostMapping(value = "/createAndUpdate")
  public ApiResponse createAndUpdateCoupon(@RequestBody CreateCouponDTO createCouponDTO) {
    ApiResponse apiResponse = new ApiResponse();
    try {
      if (createCouponDTO != null) {
        apiResponse.setData(couponService.createCoupon(createCouponDTO));
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

  @GetMapping(value = "/list")
  public ApiResponse createCoupon(@RequestParam(required = false) String type, @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate, @RequestParam(required = false) String couponName, @RequestParam(required = false) String status) {
    ApiResponse apiResponse = new ApiResponse();
    try {
      apiResponse.setData(couponService.getListCoupon(type, startDate, endDate, couponName, status));
      apiResponse.setSuccess(true);
    } catch (Exception e) {
      apiResponse.setMessage(e.getMessage());
      apiResponse.setSuccess(false);
    }

    return apiResponse;
  }

  @GetMapping(value = "/changeStatus")
  public ApiResponse changeStatusCoupon(@RequestParam String status, @RequestParam Integer couponId) {
    ApiResponse apiResponse = new ApiResponse();
    try {
      apiResponse.setData(couponService.changeStatus(status, couponId));
      apiResponse.setSuccess(true);
    } catch (Exception e) {
      apiResponse.setMessage(e.getMessage());
      apiResponse.setSuccess(false);
    }

    return apiResponse;
  }
}
