package com.fg.pation.domain;

public class RechargeOrderRequest {

    private Integer orderId;
    private String rechargeWay;
    private Integer rechargePrice;
    private String grade;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getRechargeWay() {
        return rechargeWay;
    }

    public void setRechargeWay(String rechargeWay) {
        this.rechargeWay = rechargeWay;
    }

    public Integer getRechargePrice() {
        return rechargePrice;
    }

    public void setRechargePrice(Integer rechargePrice) {
        this.rechargePrice = rechargePrice;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
