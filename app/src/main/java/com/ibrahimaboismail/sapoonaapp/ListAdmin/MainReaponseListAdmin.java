package com.ibrahimaboismail.sapoonaapp.ListAdmin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by d_200 on 3/8/2018.
 */

public class MainReaponseListAdmin {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private List<String> message = null;
    @SerializedName("data")
    @Expose
    private List<Admins> data = null;

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

    public List<Admins> getData() {
        return data;
    }

    public void setData(List<Admins> data) {
        this.data = data;
    }
}
