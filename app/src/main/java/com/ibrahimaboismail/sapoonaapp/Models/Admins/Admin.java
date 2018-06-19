package com.ibrahimaboismail.sapoonaapp.Models.Admins;

import com.google.gson.annotations.SerializedName;

/**
 * Created by d_200 on 5/10/2018.
 */

public class Admin {


    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("email")
    public String email;
    @SerializedName("created_at")
    public String created_at;
    @SerializedName("updated_at")
    public String updated_at;
    @SerializedName("profile")
    public Profile profile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", profile=" + profile +
                '}';
    }

    public static class Profile {
        @SerializedName("id")
        public int id;
        @SerializedName("user_id")
        public int user_id;
        @SerializedName("per_mang_name")
        public String per_mang_name;
        @SerializedName("imag")
        public String imag;
        @SerializedName("moblie_nm1")
        public int moblie_nm1;
        @SerializedName("moblie_nm2")
        public int moblie_nm2;
        @SerializedName("bio")
        public String bio;
        @SerializedName("phone")
        public int phone;
        @SerializedName("created_at")
        public String created_at;
        @SerializedName("updated_at")
        public String updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getPer_mang_name() {
            return per_mang_name;
        }

        public void setPer_mang_name(String per_mang_name) {
            this.per_mang_name = per_mang_name;
        }

        public String getImag() {
            return imag;
        }

        public void setImag(String imag) {
            this.imag = imag;
        }

        public int getMoblie_nm1() {
            return moblie_nm1;
        }

        public void setMoblie_nm1(int moblie_nm1) {
            this.moblie_nm1 = moblie_nm1;
        }

        public int getMoblie_nm2() {
            return moblie_nm2;
        }

        public void setMoblie_nm2(int moblie_nm2) {
            this.moblie_nm2 = moblie_nm2;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public int getPhone() {
            return phone;
        }

        public void setPhone(int phone) {
            this.phone = phone;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        @Override
        public String toString() {
            return "Profile{" +
                    "id=" + id +
                    ", user_id=" + user_id +
                    ", per_mang_name='" + per_mang_name + '\'' +
                    ", imag='" + imag + '\'' +
                    ", moblie_nm1=" + moblie_nm1 +
                    ", moblie_nm2=" + moblie_nm2 +
                    ", bio='" + bio + '\'' +
                    ", phone=" + phone +
                    ", created_at='" + created_at + '\'' +
                    ", updated_at='" + updated_at + '\'' +
                    '}';
        }
    }
}
