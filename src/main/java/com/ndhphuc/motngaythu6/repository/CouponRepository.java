package com.ndhphuc.motngaythu6.repository;

import com.ndhphuc.motngaythu6.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

  Coupon findCouponsByCouponId(Integer couponId);

  @Query(value = "select * from coupons where is_delete = 0 and case when (:status is not null) then (status = :status) else 1=1 end " +
          "and case when (:type is not null) then (type_coupon = :type) else  1=1 end " +
          "and coupon_name like :couponName " +
          "and case when (:startDate is not null and :endDate is not null) then (create_date between :statDate and :endDate) else 1=1 end",nativeQuery = true)
  List<Coupon> getListCoupon(String type,String startDate,String endDate,String couponName,String status);
}
