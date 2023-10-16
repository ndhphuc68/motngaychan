package com.ndhphuc.motngaythu6.dto;

import com.ndhphuc.motngaythu6.model.Category;
import com.ndhphuc.motngaythu6.model.ProductCategory;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Getter
public class ProductDTO {
    private String productCode;

    private String productName;

    private String productUnit;

    private BigDecimal productPrice;

    private String productImage;

    private String description;

    private Date createDate;

    private List<Integer> categoryId;

    private List<Category> categories;

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setCategoryId(List<Integer> categoryId) {
        this.categoryId = categoryId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
