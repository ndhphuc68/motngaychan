package com.ndhphuc.motngaythu6.service;

import com.ndhphuc.motngaythu6.dto.CreateProductSkuPriceDTO;
import com.ndhphuc.motngaythu6.model.Product;
import com.ndhphuc.motngaythu6.model.ProductSkuPrice;
import com.ndhphuc.motngaythu6.repository.ProductRepository;
import com.ndhphuc.motngaythu6.repository.ProductSkuPriceRepository;
import com.ndhphuc.motngaythu6.utils.ActionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductSkuPriceService {

  @Autowired
  ProductSkuPriceRepository productSkuPriceRepository;

  @Autowired
  ProductRepository productRepository;

  public CreateProductSkuPriceDTO createProductSkuPrice(CreateProductSkuPriceDTO productSkuPriceDTO) {
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

  public CreateProductSkuPriceDTO updateProductSkuPrice(CreateProductSkuPriceDTO productSkuPriceDTO) throws Exception {
    ProductSkuPrice productSku = productSkuPriceRepository.findProductSkuPriceById(productSkuPriceDTO.getProductSkuCode());
    if (productSku != null) {
      productSku.setIsDelete(1);
      productSku.setUpdateDate(new Date());
      productSkuPriceRepository.save(productSku);

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
    } else {
      throw new Exception("Sản phẩm không tồn tại");
    }
  }

  public List<Map<String, Objects>> getListProductSkuPrice(Integer isSale, String searchText, String startDate, String endDate) {
    List<Integer> listIsSale = new ArrayList<>();
    if (isSale == null) {
      listIsSale.add(0);
      listIsSale.add(1);
    } else {
      listIsSale.add(isSale);
    }

    if (searchText != null) {
      searchText = "%" + searchText + "%";
    } else {
      searchText = "%";
    }
    return productSkuPriceRepository.getListProductSkuPrice(listIsSale, startDate, endDate, searchText);
  }

  public boolean actionProductSku(Integer productSkuCode, String type) {
    ProductSkuPrice productSkuPrice = productSkuPriceRepository.findProductSkuPriceById(productSkuCode);
    if (productSkuPrice != null) {
      if (ActionUser.DELETE.getAction().equals(type)) {
        productSkuPrice.setIsDelete(1);
        productSkuPriceRepository.save(productSkuPrice);
        return true;
      } else if (ActionUser.BLOCK.getAction().equals(type)) {
        if (productSkuPrice.getIsSale() == 1) {
          productSkuPrice.setIsSale(0);
        } else {
          productSkuPrice.setIsSale(1);
        }
        productSkuPriceRepository.save(productSkuPrice);
        return true;
      }
    }
    return false;
  }
}
