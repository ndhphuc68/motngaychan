package com.ndhphuc.motngaythu6.service;

import com.ndhphuc.motngaythu6.dto.CreateCouponDTO;
import com.ndhphuc.motngaythu6.model.Coupon;
import com.ndhphuc.motngaythu6.repository.CouponRepository;
import com.ndhphuc.motngaythu6.utils.StatusCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CouponService {

  @Autowired
  CouponRepository couponRepository;

  public CreateCouponDTO createCoupon(CreateCouponDTO createCouponDTO) throws Exception {
    if (createCouponDTO.getCouponId() != null) {
      Coupon coupon = couponRepository.findCouponsByCouponId(createCouponDTO.getCouponId());
      if (coupon != null && coupon.getStatus().equals(StatusCoupon.WRITE.getStatus())) {
        coupon.setCouponName(createCouponDTO.getCouponName());
        coupon.setDescription(createCouponDTO.getDescription());
        coupon.setStartDate(new Date(createCouponDTO.getStartDate()));
        coupon.setEndDate(new Date(createCouponDTO.getEndDate()));
        coupon.setReducedValue(createCouponDTO.getReducedValue());
        coupon.setTotalInvoice(createCouponDTO.getTotalInvoice());
        coupon.setTypeCoupon(createCouponDTO.getTypeCoupon());

        coupon.setUpdateDate(new Date());

        couponRepository.save(coupon);

      } else {
        throw new Exception("Không thể chỉnh sửa coupon!");
      }
    } else {
      Coupon coupon = new Coupon();
      coupon.setCouponName(createCouponDTO.getCouponName());
      coupon.setDescription(createCouponDTO.getDescription());
      coupon.setStatus(StatusCoupon.WRITE.getStatus());
      coupon.setStartDate(new Date(createCouponDTO.getStartDate()));
      coupon.setEndDate(new Date(createCouponDTO.getEndDate()));
      coupon.setReducedValue(createCouponDTO.getReducedValue());
      coupon.setTotalInvoice(createCouponDTO.getTotalInvoice());
      coupon.setTypeCoupon(createCouponDTO.getTypeCoupon());

      coupon.setCreateDate(new Date());
      coupon.setUpdateDate(new Date());
      coupon.setIsDelete(0);

      couponRepository.save(coupon);
    }

    return createCouponDTO;
  }

  public List<Coupon> getListCoupon(String type, String startDate, String endDate, String couponName, String status) {
    if (couponName == null) {
      couponName = "%";
    } else {
      couponName = "%" + couponName + "%";
    }

    return couponRepository.getListCoupon(type, startDate, endDate, couponName, status);
  }

  public Coupon changeStatus(String status, Integer couponId) {
    Coupon coupon = couponRepository.findCouponsByCouponId(couponId);
    if (coupon != null) {
      coupon.setUpdateDate(new Date());
      coupon.setStatus(status);
      couponRepository.save(coupon);
    }
    return coupon;
  }
}
