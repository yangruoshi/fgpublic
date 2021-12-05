package com.fg.drools.domain;

public class Product {
    /**
     * 商品类型
     */
   public enum ProductType {
        General, Brand
    }
    private  ProductType productType;
    private Double retailPrice;
    private String productName;

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
