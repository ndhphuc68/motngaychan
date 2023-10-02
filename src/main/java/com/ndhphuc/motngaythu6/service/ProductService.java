package com.ndhphuc.motngaythu6.service;

import com.ndhphuc.motngaythu6.dto.ProductDTO;
import com.ndhphuc.motngaythu6.model.Product;
import com.ndhphuc.motngaythu6.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product createProduct(ProductDTO productDTO) {
        Product product = new Product();

        product.setProductName(productDTO.getProductName());
        product.setProductCode(productDTO.getProductCode());
        product.setProductPrice(productDTO.getProductPrice());
        product.setProductUnit(productDTO.getProductUnit());
        product.setCreateDate(new Date());
        product.setUpdateDate(new Date());
        product.setIsSale(0);

        productRepository.save(product);
        return product;
    }

    public List<Product> getListProduct(){
        return productRepository.findAll();
    }
}
