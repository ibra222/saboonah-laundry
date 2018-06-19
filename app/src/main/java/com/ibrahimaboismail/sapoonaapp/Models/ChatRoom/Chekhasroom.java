package com.ibrahimaboismail.sapoonaapp.Models.ChatRoom;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by d_200 on 2/13/2018.
 */

public class Chekhasroom {
    @SerializedName("ishasroom")
    @Expose
    private Boolean ishasroom;
    @SerializedName("data")
    @Expose
    private List<Room> data = null;

    public Boolean getIshasroom() {
        return ishasroom;
    }

    public void setIshasroom(Boolean ishasroom) {
        this.ishasroom = ishasroom;
    }

    public List<Room> getData() {
        return data;
    }

    public void setData(List<Room> data) {
        this.data = data;
    }
}
