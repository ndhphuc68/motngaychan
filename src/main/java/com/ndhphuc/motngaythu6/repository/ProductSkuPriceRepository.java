package com.ndhphuc.motngaythu6.repository;

import com.ndhphuc.motngaythu6.model.ProductSkuPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public interface ProductSkuPriceRepository extends JpaRepository<ProductSkuPrice, Integer> {

  ProductSkuPrice findProductSkuPriceById(Integer id);

  @Query(value = "select * from product_sku_price psk join products p on p.product_code = psk.product_code where p.is_sale = 0 and psk.is_delete = 0 " +
          "and psk.is_sale in :isSale and " +
          "case when ( :startDate is not null and :endDate is not null) then(psk.create_date between CAST(:startDate AS TIMESTAMP) and CAST(:endDate AS TIMESTAMP)) else (1=1) end " +
          "and (psk.product_code like :searchText or p.product_name like :searchText or psk.name_sale like :searchText )",nativeQuery = true)
  List<Map<String, Objects>> getListProductSkuPrice(List<Integer> isSale, String startDate ,String endDate,String searchText);
}
