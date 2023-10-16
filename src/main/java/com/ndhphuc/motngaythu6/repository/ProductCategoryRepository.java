package com.ndhphuc.motngaythu6.repository;

import com.ndhphuc.motngaythu6.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

  List<ProductCategory> findByProductCode(String productCode);
}
