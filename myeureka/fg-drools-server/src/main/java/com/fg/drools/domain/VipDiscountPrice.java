package com.fg.drools.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 不同等级的会员购买不同类型商品后的优惠价格
 */
@JsonPropertyOrder({"vipevel","product","payPrice"})
public class VipDiscountPrice {
    /**
     * 会员等级
     */
   public enum VipLevel {
        General, Platinum
    }
    /**
     * 价格
     */

    private double payPrice;
    private Product product;
    private VipLevel vipLevel;

    public double getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(double payPrice) {
        this.payPrice = payPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public VipLevel getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(VipLevel vipLevel) {
        this.vipLevel = vipLevel;
    }

}
