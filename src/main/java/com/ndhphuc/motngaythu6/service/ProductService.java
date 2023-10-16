package com.ndhphuc.motngaythu6.service;

import com.ndhphuc.motngaythu6.dto.ProductDTO;
import com.ndhphuc.motngaythu6.model.Category;
import com.ndhphuc.motngaythu6.model.Product;
import com.ndhphuc.motngaythu6.model.ProductCategory;
import com.ndhphuc.motngaythu6.repository.CategoryRepository;
import com.ndhphuc.motngaythu6.repository.ProductCategoryRepository;
import com.ndhphuc.motngaythu6.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {

  @Autowired
  ProductRepository productRepository;

  @Autowired
  ProductCategoryRepository productCategoryRepository;

  @Autowired
  CategoryRepository categoryRepository;

  public Product createProduct(ProductDTO productDTO) {
    Product product = new Product();
    product.setProductName(productDTO.getProductName());
    product.setProductCode(productDTO.getProductCode());
    product.setProductPrice(productDTO.getProductPrice());
    product.setProductUnit(productDTO.getProductUnit());
    product.setProductImage(productDTO.getProductImage());
    product.setDescription(productDTO.getDescription());
    product.setCreateDate(new Date());
    product.setUpdateDate(new Date());
    product.setIsSale(0);
    productRepository.save(product);

    if (!productDTO.getCategoryId().isEmpty()) {
      for (Integer id : productDTO.getCategoryId()) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(id);
        productCategory.setProductCode(product.getProductCode());
        productCategory.setCareateDate(new Date());
        productCategoryRepository.save(productCategory);
      }
    }

    return product;
  }

  public List<ProductDTO> getListProduct(Integer isSale,String searchText,Date startDate,Date endDate) {
    List<ProductDTO> productDTOList = new ArrayList<>();
    List<Integer> listIsSale = new ArrayList<>();
    if(isSale == null){
      listIsSale.add(0);
      listIsSale.add(1);
    }else{
      listIsSale.add(isSale);
    }
    List<Product> list = productRepository.getListProduct(listIsSale,startDate,endDate,searchText);
    if (!list.isEmpty()) {
      for (Product product : list) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductCode(product.getProductCode());
        productDTO.setProductName(product.getProductName());
        productDTO.setProductUnit(product.getProductUnit());
        productDTO.setDescription(product.getDescription());
        productDTO.setCreateDate(product.getCreateDate());
        productDTO.setProductPrice(product.getProductPrice());

        //get list category product
        List<ProductCategory> categories = productCategoryRepository.findByProductCode(product.getProductCode());
        List<Integer> integers = new ArrayList<>();
        List<Category> categoryList = new ArrayList<>();
        if (!categories.isEmpty()) {
          for (ProductCategory productCategory : categories) {
            Category category = categoryRepository.findCategoryById(productCategory.getCategoryId());
            if (category != null) {
              categoryList.add(category);
            }
            integers.add(productCategory.getCategoryId());
          }
        }
        productDTO.setCategories(categoryList);
        productDTO.setCategoryId(integers);
        productDTOList.add(productDTO);
      }
    }

    return productDTOList;
  }
}
