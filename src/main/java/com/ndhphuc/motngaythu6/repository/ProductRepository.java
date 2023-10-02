package com.ndhphuc.motngaythu6.repository;

import com.ndhphuc.motngaythu6.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
