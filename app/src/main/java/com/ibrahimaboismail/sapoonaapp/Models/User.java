package com.ibrahimaboismail.sapoonaapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by d_200 on 1/26/2018.
 */

public class User extends RealmObject {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("mobile_number")
    @Expose
    private String mobileNumber;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("number_orders")
    @Expose
    private Integer numberOrders;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("root_id")
    @Expose
    private Integer root_id;

    public User(String username, String mobileNumber, String address, String password, Integer numberOrders, String token, String updatedAt, String createdAt, Integer id, Integer root_id) {
        this.username = username;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.password = password;
        this.numberOrders = numberOrders;
        this.token = token;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.id = id;
        this.root_id = root_id;
    }


    public User() {

    }

    public Integer getRoot_id() {
        return root_id;
    }

    public void setRoot_id(Integer root_id) {
        this.root_id = root_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getNumberOrders() {
        return numberOrders;
    }

    public void setNumberOrders(Integer numberOrders) {
        this.numberOrders = numberOrders;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", numberOrders=" + numberOrders +
                ", token='" + token + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", id=" + id +
                ", root_id=" + root_id +
                '}';
    }
}
