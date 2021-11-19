package com.example.ssis_tracker.api.agenda;

import com.example.ssis_tracker.model.Agenda;
import com.example.ssis_tracker.model.ApiResponse;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiServiceAgenda {

    @POST("agenda")
    Call<ApiResponse> AgendarNuevoTema(@Body JsonObject tema);

    @GET("agenda")
    Call<ArrayList<Agenda>> ListarTemaAgenda();

    @POST("agenda/{id}/{rol}")
    Call<ApiResponse> EliminarTemaAgenda(@Path("id") int temaId , @Path("rol") int idRol);
}
