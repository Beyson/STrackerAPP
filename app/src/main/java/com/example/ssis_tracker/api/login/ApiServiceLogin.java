package com.example.ssis_tracker.api.login;

import com.example.ssis_tracker.model.ApiResponse;
import com.example.ssis_tracker.model.Login;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiServiceLogin {

    @POST("login")
    Call<Login> getLogin(@Body JsonObject Login);

    @POST("login/password/change")
    Call<ApiResponse> CambiarPassword(@Body JsonObject Login);

}
