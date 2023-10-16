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


  @Query(value = "select * from products p join product_category pc on p.product_code = pc.product_code where p.is_sale in :isSale and case when ( :startDate is not null and :endDate is not null) then(p.create_date between :startDate and :endDate) else (1=1) end and p.product_code like :searchText or p.product_name like :searchText ", nativeQuery = true)
  List<Product> getListProduct(List<Integer> isSale, Date startDate ,Date endDate,String searchText);
}
