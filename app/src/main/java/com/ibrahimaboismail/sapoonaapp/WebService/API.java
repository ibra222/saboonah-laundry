package com.ibrahimaboismail.sapoonaapp.WebService;

import com.ibrahimaboismail.sapoonaapp.ListAdmin.MainReaponseListAdmin;
import com.ibrahimaboismail.sapoonaapp.Models.Admins.MainAdminDryClensResponse;
import com.ibrahimaboismail.sapoonaapp.Models.Cart.MainCartResponse;
import com.ibrahimaboismail.sapoonaapp.Models.ChatRoom.Chekhasroom;
import com.ibrahimaboismail.sapoonaapp.Models.ChatRoom.MainChatRoomResponse;
import com.ibrahimaboismail.sapoonaapp.Models.ChatRoom.MainGetRoom;
import com.ibrahimaboismail.sapoonaapp.Models.Main;
import com.ibrahimaboismail.sapoonaapp.Models.MainAddCartResponse;
import com.ibrahimaboismail.sapoonaapp.Models.MainAdsResponse;
import com.ibrahimaboismail.sapoonaapp.Models.MainItmeRespons;
import com.ibrahimaboismail.sapoonaapp.Models.MainRatingReqiste;
import com.ibrahimaboismail.sapoonaapp.Models.MainSingupResponse;
import com.ibrahimaboismail.sapoonaapp.Models.Message.MainMessageResponse;
import com.ibrahimaboismail.sapoonaapp.Models.Orders.MainOrdersResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by d_200 on 12/18/2017.
 */

public interface API {

//    @FormUrlEncoded
//    @POST("login")
//    Call<MainPatient>loginUser(@Field("id_number") int id_number, @Field("password") String password);
//
//    @GET("allenter")
//    Call<MainEnterpise>listent();

    @GET("ads/adsbytype/{root_id}/{type}")
    Call<MainAdsResponse>mainads(@Path("root_id") int root_id,
                                 @Path("type") int type);


    @GET("ads/adsbytype/غسيل")
    Call<MainAdsResponse>mainads2();

    @GET("itmes/all/{root_id}")
    Call<MainItmeRespons>mainitem(@Path("root_id") int root_id);


     @FormUrlEncoded
     @POST("cart/add")
     Call<MainAddCartResponse>AddItemToCart(@Field("item_id") int item_id,
                                            @Field("user_id") int user_id,
                                            @Field("quantity") int quantity,
                                            @Field("type") int type);

    @FormUrlEncoded
    @POST("cart/approval_request")
    Call<MainAddCartResponse>approval_request(@Field("user_id") int user_id,
                                              @Field("root_id") int root_id,
                                              @Field("address") String address,
                                              @Field("time_of_receipt") String time_of_receipt,
                                              @Field("delivery_time") String delivery_time);

    @FormUrlEncoded
    @POST("auth/singup")
    Call<MainSingupResponse>singup(@Field("username") String username,
                                   @Field("mobile_number") int mobile_number,
                                   @Field("root_id") int root_id,
                                   @Field("address") String address,
                                   @Field("password") String password
                                  );

    @FormUrlEncoded
    @POST("auth/login")
    Call<MainSingupResponse>login(@Field("mobile_number") int mobile_number,
                                   @Field("password") String password
                                   );

    @FormUrlEncoded
    @POST("cart/list")
    Call<MainCartResponse>cart(@Field("user_id") int user_id);


    @GET("order/order/{id}")
    Call<MainOrdersResponse>getorders(@Path("id") int user_id);

    @FormUrlEncoded
    @POST("notifcation/add")
    Call<MainCartResponse>adds(@Field("user_id") int user_id,@Field("token") String token,@Field("root_id") int root_id);

    @FormUrlEncoded
    @POST("chat/addroom")
    Call<MainChatRoomResponse>addroom(@Field("room_name") String room_name,
                                      @Field("room_desc") String room_desc,
                                      @Field("user_id") int user_id);

    @GET("chat/ishascreateroom/{user_id}")
    Call<Chekhasroom>chek(@Path("user_id") int user_id);

    @FormUrlEncoded
    @POST("room/addmessagetoroom")
    Call<MainChatRoomResponse>addmessagetoroom(
                                      @Field("user_id") int user_id,
                                      @Field("room_id") int room_id,
                                      @Field("user_name") String user_name,
                                      @Field("contents") String content,
                                      @Field("type") int type
                                      );

    @GET("chat/getuserchatroom/{user_id}")
    Call<MainGetRoom>getRoom(@Path("user_id") int user_id);


    @GET("room/getmessagetoroom/{room_id}")
    Call<MainMessageResponse>getmessagetoroom(@Path("room_id") int room_id);

    @FormUrlEncoded
    @POST("cart/destroy")
    Call<Main>deleit(@Field("id") int id);

    @FormUrlEncoded
    @POST("cart/edit")
    Call<Main>edit(
                    @Field("id") int id,
                    @Field("quantity") int quantity,
                    @Field("type") int type
                   );

    @GET("chat/checkuserhaschat/{user_id}")
    Call<Chekhasroom>chekmsehi(@Path("user_id") int user_id);

    @GET("auth/getUsers")
    Call<MainReaponseListAdmin>getAdmin();

    @FormUrlEncoded
    @POST("rating/addriting")
    Call<MainRatingReqiste>addriting(
            @Field("order_number") int order_number,
            @Field("user_id") int user_id,
            @Field("username") String username,
            @Field("mobile_number") String mobile_number,
            @Field("address") String address,
            @Field("rate") float rate,
            @Field("note") String note,
            @Field("root_id") int root_id
    );

     @GET("auth/alluserAdmin")
     Call<MainAdminDryClensResponse>alluserAdmin();

}
