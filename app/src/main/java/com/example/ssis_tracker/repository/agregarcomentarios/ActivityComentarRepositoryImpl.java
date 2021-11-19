package com.example.ssis_tracker.repository.agregarcomentarios;

import com.google.android.material.snackbar.Snackbar;
import android.view.View;
import com.example.ssis_tracker.api.actividades.ApiAdapterActividades;
import com.example.ssis_tracker.api.actividades.ApiServiceActividades;
import com.example.ssis_tracker.model.ApiResponse;
import com.example.ssis_tracker.presenter.agregarcomentarios.ActivityComentarPresenter;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityComentarRepositoryImpl implements ActivityComentarRepository {

    private ActivityComentarPresenter activityComentarPresenter;
    private ApiServiceActividades apiServiceActividades;
    private View view;

    public ActivityComentarRepositoryImpl(ActivityComentarPresenter activityComentarPresenter , View view){
        this.activityComentarPresenter = activityComentarPresenter;
        this.apiServiceActividades = new ApiAdapterActividades().getClientService();
        this.view = view;
    }

    @Override
    public void AgregarComentario(JsonObject jsonComentario) {
        Call<ApiResponse>  Call = this.apiServiceActividades.agregarComentarios(jsonComentario);
        Call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(retrofit2.Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.body().getState().equals("1")){
                    activityComentarPresenter.ComentarioAgregado(1);
                }else {
                    Snackbar.make(view,response.body().getDescription(),Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ApiResponse> call, Throwable t) {
                Snackbar.make(view,"Error al registrar el comentario",Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
