package com.example.ssis_tracker.api.login;

import com.example.ssis_tracker.api.ConfigAPI;
import com.example.ssis_tracker.api.performance.ApiServicePerformance;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiAdapterLogin {

    public ApiServiceLogin getClientService(){
        Retrofit objRetrofit = new Retrofit.Builder()
                .baseUrl(new ConfigAPI().getURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return objRetrofit.create(ApiServiceLogin.class);
    }
}
