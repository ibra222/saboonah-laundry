package com.ibrahimaboismail.sapoonaapp.Models.Orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by d_200 on 2/4/2018.
 */

public class Order {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("order_number")
    @Expose
    private Integer orderNumber;
    @SerializedName("time_of_receipt")
    @Expose
    private String timeOfReceipt;
    @SerializedName("delivery_time")
    @Expose
    private String deliveryTime;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("cashIn")
    @Expose
    private String cashIn;
    @SerializedName("payment")
    @Expose
    private String payment;
    @SerializedName("change")
    @Expose
    private String change;
    @SerializedName("user_id")
    @Expose
    private Integer userId;

    @SerializedName("root_id")
    @Expose
    private Integer rootId;

    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Order(Integer id, Integer orderNumber, String timeOfReceipt, String deliveryTime,
                 String total, Integer status, String cashIn, String payment,
                 String change, Integer userId, Integer rootId, String createdAt, String updatedAt) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.timeOfReceipt = timeOfReceipt;
        this.deliveryTime = deliveryTime;
        this.total = total;
        this.status = status;
        this.cashIn = cashIn;
        this.payment = payment;
        this.change = change;
        this.userId = userId;
        this.rootId = rootId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public Order() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getTimeOfReceipt() {
        return timeOfReceipt;
    }

    public void setTimeOfReceipt(String timeOfReceipt) {
        this.timeOfReceipt = timeOfReceipt;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCashIn() {
        return cashIn;
    }

    public void setCashIn(String cashIn) {
        this.cashIn = cashIn;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRootId() {
        return rootId;
    }

    public void setRootId(Integer rootId) {
        this.rootId = rootId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNumber=" + orderNumber +
                ", timeOfReceipt='" + timeOfReceipt + '\'' +
                ", deliveryTime='" + deliveryTime + '\'' +
                ", total='" + total + '\'' +
                ", status=" + status +
                ", cashIn='" + cashIn + '\'' +
                ", payment='" + payment + '\'' +
                ", change='" + change + '\'' +
                ", userId=" + userId +
                ", rootId=" + rootId +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
