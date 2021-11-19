package com.example.ssis_tracker.repository.agenda;

import com.example.ssis_tracker.api.agenda.ApiAdapterAgenda;
import com.example.ssis_tracker.api.agenda.ApiServiceAgenda;
import com.example.ssis_tracker.model.ApiResponse;
import com.example.ssis_tracker.presenter.agenda.AgendarTemaActivityPresenter;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgendarTemaActivityRepositoryImpl implements AgendarTemaActivityRepository {


    private AgendarTemaActivityPresenter agendarTemaActivityPresenter;
    private ApiServiceAgenda servicesAgenda;

    public AgendarTemaActivityRepositoryImpl(AgendarTemaActivityPresenter agendarTemaActivityPresenter){
        this.agendarTemaActivityPresenter = agendarTemaActivityPresenter;
        this.servicesAgenda = new ApiAdapterAgenda().getClientService();
    }

    @Override
    public void AgendarTema(JsonObject tema) {

        Call<ApiResponse> call = this.servicesAgenda.AgendarNuevoTema(tema);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                agendarTemaActivityPresenter.TemaRegistrado(true);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                agendarTemaActivityPresenter.TemaRegistrado(false);
            }
        });
    }
}
