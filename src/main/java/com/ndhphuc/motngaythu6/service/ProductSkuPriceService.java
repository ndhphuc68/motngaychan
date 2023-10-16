package com.ndhphuc.motngaythu6.service;

import com.ndhphuc.motngaythu6.dto.CreateProductSkuPriceDTO;
import com.ndhphuc.motngaythu6.model.Product;
import com.ndhphuc.motngaythu6.model.ProductSkuPrice;
import com.ndhphuc.motngaythu6.repository.ProductRepository;
import com.ndhphuc.motngaythu6.repository.ProductSkuPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProductSkuPriceService {

  @Autowired
  ProductSkuPriceRepository productSkuPriceRepository;

  @Autowired
  ProductRepository productRepository;

  public CreateProductSkuPriceDTO createProductSkuPrice(CreateProductSkuPriceDTO productSkuPriceDTO) {
    Product product = productRepository.findProductByProductCode(productSkuPriceDTO.getProductCode());
    if (product != null) {
      ProductSkuPrice productSkuPrice = new ProductSkuPrice();
      productSkuPrice.setProductCode(productSkuPriceDTO.getProductCode());
      productSkuPrice.setNameSale(productSkuPriceDTO.getNameSale());
      productSkuPrice.setCreateDate(new Date());
      productSkuPrice.setUpdateDate(new Date());
      productSkuPrice.setPrice(productSkuPriceDTO.getPrice());
      productSkuPrice.setIsDelete(0);
      productSkuPrice.setIsSale(0);
      productSkuPriceRepository.save(productSkuPrice);
      return productSkuPriceDTO;
    }
    return null;
  }
}
