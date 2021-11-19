package com.example.ssis_tracker.api.direcciones;


import com.example.ssis_tracker.model.Direccion;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceDirecciones {

    @GET("direcciones")
    Call<ArrayList<Direccion>> getDirecciones();
}
