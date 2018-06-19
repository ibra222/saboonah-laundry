package com.ibrahimaboismail.sapoonaapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by d_200 on 4/18/2018.
 */

public class MainRatingReqiste {

    @SerializedName("success")
    @Expose
    public boolean success;
    @SerializedName("message")
    @Expose
    public List<String> message;
    @SerializedName("data")
    @Expose
    public Data data;

    public static class Data {
        @SerializedName("order_number")
        public String order_number;
        @SerializedName("user_id")
        public String user_id;
        @SerializedName("username")
        public String username;
        @SerializedName("mobile_number")
        public String mobile_number;
        @SerializedName("address")
        public String address;
        @SerializedName("rate")
        public String rate;
        @SerializedName("note")
        public String note;
        @SerializedName("updated_at")
        public String updated_at;
        @SerializedName("created_at")
        public String created_at;
        @SerializedName("id")
        public int id;
    }
}
