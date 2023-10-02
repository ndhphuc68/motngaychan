package com.ndhphuc.motngaythu6.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ProductDTO {
    private String productCode;

    private String productName;

    private String productUnit;

    private BigDecimal productPrice;

    private BigInteger productImage;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigInteger getProductImage() {
        return productImage;
    }

    public void setProductImage(BigInteger productImage) {
        this.productImage = productImage;
    }
}
