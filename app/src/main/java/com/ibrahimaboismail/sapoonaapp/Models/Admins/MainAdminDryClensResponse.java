package com.ibrahimaboismail.sapoonaapp.Models.Admins;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by d_200 on 5/10/2018.
 */

public class MainAdminDryClensResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private List<String> message = null;
    @SerializedName("data")
    @Expose
    private List<Admin> data = null;

    public MainAdminDryClensResponse(Boolean success, List<String> message, List<Admin> data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public MainAdminDryClensResponse() {

    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public List<Admin> getData() {
        return data;
    }

    public void setData(List<Admin> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MainAdminDryClensResponse{" +
                "success=" + success +
                ", message=" + message +
                ", data=" + data +
                '}';
    }
}
