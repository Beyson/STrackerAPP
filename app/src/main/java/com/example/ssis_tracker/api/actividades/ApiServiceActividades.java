package com.example.ssis_tracker.api.actividades;

import com.example.ssis_tracker.model.Actividad;
import com.example.ssis_tracker.model.ActividadesDependientes;
import com.example.ssis_tracker.model.ApiResponse;
import com.example.ssis_tracker.model.Comentario;
import com.example.ssis_tracker.model.Meta;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiServiceActividades {

    @GET("actividades/get/1/-1/-1/2")
    Call<ArrayList<Actividad>> getallActividades();

    @GET("actividades/get/1/{direccion}/{codigo}/{tipo}")
    Call<ArrayList<Actividad>> getActividades(@Path("direccion") int direccion , @Path("codigo") int codigo , @Path("tipo") int tipo);

    @GET("actividad/get/comentarios/{actividad}")
    Call<ArrayList<Comentario>> getComentarios(@Path("actividad") int actividad);

    @GET("metas/actividad/{actividad}")
    Call<ArrayList<Meta>> getMetas(@Path("actividad") int actividad);

    @GET("actividad/get/dependencias/{actividad}")
    Call<ArrayList<ActividadesDependientes>> getActividadeDependientes(@Path("actividad") int actividad);

    @POST("actividad/add/comentario")
    Call<ApiResponse> agregarComentarios(@Body JsonObject comentario);

}

