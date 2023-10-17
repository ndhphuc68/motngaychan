package com.ndhphuc.motngaythu6.repository;

import com.ndhphuc.motngaythu6.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

  Product findProductByProductCode(String productCode);


  @Query(value = "select p.* from products p join product_category pc on p.product_code = pc.product_code where p.is_sale in :isSale and " +
          "case when ( :startDate is not null and :endDate is not null) then(p.create_date between CAST(:startDate AS TIMESTAMP) and CAST(:endDate AS TIMESTAMP)) else (1=1) end" +
          " and (p.product_code like :searchText or p.product_name like :searchText ) and p.is_delete = 0 group by p.product_code order by p.create_date desc ", nativeQuery = true)
  List<Product> getListProduct(List<Integer> isSale, String startDate ,String endDate,String searchText);
}
