package com.entertaiment.demo.api;

import com.entertaiment.demo.model.LogInResult;
import com.entertaiment.demo.model.ProfileModel;
import com.entertaiment.demo.model.VerifyRegistered;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("request-phone-verification")
    @Headers("X-Api-Key: Ikf5qGHzDF0rlz-Cu-7pYOHlRCLN8L9q")
    Call<LogInResult> requestOTP(@Field("phone_country_code") String phoneCode, @Field("phone_number") String phoneNumber, @Field("receive_via") String via);

    @FormUrlEncoded
    @POST("verify-phone")
    @Headers("X-Api-Key: Ikf5qGHzDF0rlz-Cu-7pYOHlRCLN8L9q")
    Call<VerifyRegistered> verifyRegistered(@Field("phone_country_code") String phoneCode, @Field("phone_number") String phoneNumber, @Field("otp") String via);

    @POST("me/profile")
    @Headers("X-Api-Key: Ikf5qGHzDF0rlz-Cu-7pYOHlRCLN8L9q")
    Call<ProfileModel> createProfile(@Header("Authorization") String token, @Body Map<String, Object> params);

}
