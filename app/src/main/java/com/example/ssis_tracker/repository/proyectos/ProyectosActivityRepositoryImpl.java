package com.example.ssis_tracker.repository.proyectos;

import com.example.ssis_tracker.api.proyectos.ApiAdapterProyectos;
import com.example.ssis_tracker.api.proyectos.ApiServiceProyectos;
import com.example.ssis_tracker.model.ApiResponse;
import com.example.ssis_tracker.model.Proyecto;
import com.example.ssis_tracker.presenter.llamadosatencion.LlamadosAtencionPresenter;
import com.example.ssis_tracker.presenter.proyectos.ProyectosActivityPresenter;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProyectosActivityRepositoryImpl implements ProyectosActivityRepository {

    private ProyectosActivityPresenter proyectosActivityPresenter;
    private LlamadosAtencionPresenter  llamadosAtencionPresenter;
    private ApiAdapterProyectos adapterProyectos;
    private ApiServiceProyectos serviceProyectos;
    private String strNotData = "No se detectaron datos.";

    public ProyectosActivityRepositoryImpl(ProyectosActivityPresenter proyectosActivityPresenter , LlamadosAtencionPresenter llamadosAtencionPresenter) {
        this.proyectosActivityPresenter = proyectosActivityPresenter;
        this.llamadosAtencionPresenter = llamadosAtencionPresenter;
        this.adapterProyectos = new ApiAdapterProyectos();
        this.serviceProyectos = adapterProyectos.getClientService();
    }

    @Override
    public void getProyectos(int codigo , int tipo ) {
        Call<ArrayList<Proyecto>> call = serviceProyectos.getProyectos(codigo , tipo);
        call.enqueue(new Callback<ArrayList<Proyecto>>() {
            @Override
            public void onResponse(Call<ArrayList<Proyecto>> call, Response<ArrayList<Proyecto>> response) {
                if(response.isSuccessful() && !response.body().isEmpty()){
                    proyectosActivityPresenter.showProyectos(response.body());
                }else{
                    proyectosActivityPresenter.showMessage(strNotData);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Proyecto>> call, Throwable t) {
                proyectosActivityPresenter.showMessage(t.getMessage());
            }
        });
    }

    @Override
    public void getallProyectos() {
        Call<ArrayList<Proyecto>> call = serviceProyectos.getallProyectos();
        call.enqueue(new Callback<ArrayList<Proyecto>>() {
            @Override
            public void onResponse(Call<ArrayList<Proyecto>> call, Response<ArrayList<Proyecto>> response) {
                if(response.isSuccessful() && !response.body().isEmpty()){
                    proyectosActivityPresenter.showProyectos(response.body());
                }else{
                    proyectosActivityPresenter.showMessage(strNotData);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Proyecto>> call, Throwable t) {
                proyectosActivityPresenter.showMessage(t.getMessage());
            }
        });
    }

    @Override
    public void realizarLlamado(JsonObject jsonLlamado) {
        Call<ApiResponse> call = serviceProyectos.realizarLlamado(jsonLlamado);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.isSuccessful() && response.body().getState()!="0"){
                    llamadosAtencionPresenter.ShowLlamadosdeAtencion(response.body().getDescription());
                }else{
                    proyectosActivityPresenter.showMessage(response.body().getDescription());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                proyectosActivityPresenter.showMessage(t.getMessage());
            }
        });
    }
}
