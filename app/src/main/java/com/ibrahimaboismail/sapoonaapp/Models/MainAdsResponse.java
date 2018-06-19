package com.ibrahimaboismail.sapoonaapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by d_200 on 1/29/2018.
 */

public class MainAdsResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private List<String> message = null;
    @SerializedName("data")
    @Expose
    private List<Ads> data = null;

    public MainAdsResponse(Boolean success, List<String> message, List<Ads> data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public MainAdsResponse() {

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

    public List<Ads> getData() {
        return data;
    }

    public void setData(List<Ads> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MainAdsResponse{" +
                "success=" + success +
                ", message=" + message +
                ", data=" + data +
                '}';
    }
}
