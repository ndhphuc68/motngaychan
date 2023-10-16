package com.ndhphuc.motngaythu6.repository;

import com.ndhphuc.motngaythu6.model.ProductSkuPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSkuPriceRepository extends JpaRepository<ProductSkuPrice, Integer> {
}
