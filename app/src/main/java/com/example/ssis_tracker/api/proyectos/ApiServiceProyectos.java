package com.example.ssis_tracker.api.proyectos;

import com.example.ssis_tracker.model.ApiResponse;
import com.example.ssis_tracker.model.Meta;
import com.example.ssis_tracker.model.Proyecto;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiServiceProyectos {
    @GET("proyectos/get/-1/2/1")
    Call<ArrayList<Proyecto>> getallProyectos();

    @GET("proyectos/get/{codigo}/{tipo}/1")
    Call<ArrayList<Proyecto>> getProyectos(@Path("codigo") int codigo , @Path("tipo") int tipo);

    @GET("metas/proyecto/{proyecto}")
    Call<ArrayList<Meta>> getMetas(@Path("proyecto") int proyecto);

    @POST("proyecto/llamados/atencion")
    Call<ApiResponse> realizarLlamado(@Body JsonObject LlamadoAtencion);
}
