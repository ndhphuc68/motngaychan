package com.ndhphuc.motngaythu6.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
public class ProductDTO {
    private String productCode;

    private String productName;

    private String productUnit;

    private BigDecimal productPrice;

    private BigInteger productImage;

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

    public void setProductImage(BigInteger productImage) {
        this.productImage = productImage;
    }
}
