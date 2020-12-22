package com.entertaiment.demo.client;

import com.entertaiment.demo.api.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static ApiService sRetrofit = null;
    private static final String BASE_URI = "http://appointment.dev.manadrdev.com/api/v1.3/";

    public static ApiService getClient() {
        if (sRetrofit==null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URI)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService.class);
        }
        return sRetrofit;
    }

}
