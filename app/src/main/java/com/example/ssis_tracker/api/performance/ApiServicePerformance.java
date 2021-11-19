package com.example.ssis_tracker.api.performance;

import com.example.ssis_tracker.model.ApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServicePerformance {

    @GET("/rendimiento/eficiencia")
    Call<ApiResponse> eficiencia();

    @GET("/rendimiento/procesos/registrados")
    Call<ApiResponse> registrados();

    @GET("/rendimiento/procesos/finalizados")
    Call<ApiResponse> procesosfinalizados();

    @GET("/rendimiento/procesos/atrasado")
    Call<ApiResponse> procesosatrasado();

    @GET("/rendimiento/procesos/terminados/tiempo")
    Call<ApiResponse> procesosterminadotiempo();
}
