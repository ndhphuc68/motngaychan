package com.ndhphuc.motngaythu6.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "products")
@Getter
public class Product {

    @Id
    private String productCode;

    private String productName;

    private String productUnit;

    private BigDecimal productPrice;

    private int isSale;

    private String productImage;

    private String description;

    private Date createDate;

    private Date updateDate;

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

    public void setIsSale(int isSale) {
        this.isSale = isSale;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
