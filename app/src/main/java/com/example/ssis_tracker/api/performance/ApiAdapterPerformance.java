package com.example.ssis_tracker.api.performance;

import com.example.ssis_tracker.api.ConfigAPI;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiAdapterPerformance
{

    public ApiServicePerformance getClientService(){
        Retrofit objRetrofit = new Retrofit.Builder()
                .baseUrl(new ConfigAPI().getURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return objRetrofit.create(ApiServicePerformance.class);
    }
}
