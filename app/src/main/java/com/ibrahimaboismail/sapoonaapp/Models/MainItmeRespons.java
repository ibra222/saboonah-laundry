package com.ibrahimaboismail.sapoonaapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by d_200 on 1/30/2018.
 */

public class MainItmeRespons {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private List<String> message = null;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("data")
    @Expose
    private List<Item> data = null;

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

    public List<Item> getData() {
        return data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setData(List<Item> data) {
        this.data = data;
    }
}
