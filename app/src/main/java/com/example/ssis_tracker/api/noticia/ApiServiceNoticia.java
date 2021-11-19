package com.example.ssis_tracker.api.noticia;

import com.example.ssis_tracker.model.ApiResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiServiceNoticia {
    @GET("nuevanoticia")
    Call<ApiResponse> EnviarNoticia();

    @Multipart
    @POST("/CargarImagenNoticia")
    Call<ApiResponse> CargarImagenesNoticia(@Part MultipartBody.Part Imagen , @Part("ImgNoticia")RequestBody requestBody);
}
