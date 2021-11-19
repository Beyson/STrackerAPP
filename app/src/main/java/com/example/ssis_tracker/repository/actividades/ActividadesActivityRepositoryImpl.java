package com.example.ssis_tracker.repository.actividades;

import com.example.ssis_tracker.api.actividades.ApiAdapterActividades;
import com.example.ssis_tracker.api.actividades.ApiServiceActividades;
import com.example.ssis_tracker.model.Actividad;
import com.example.ssis_tracker.model.Comentario;
import com.example.ssis_tracker.presenter.actividades.ActividadesActivityPresenter;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActividadesActivityRepositoryImpl implements ActividadesActivityRepository {

    private ActividadesActivityPresenter actividadesActivityPresenter;
    private ApiServiceActividades serviceActividades;
    private String strNotData = "No se detectaron datos.";

    public ActividadesActivityRepositoryImpl(ActividadesActivityPresenter actividadesActivityPresenter) {
        this.actividadesActivityPresenter = actividadesActivityPresenter;
        serviceActividades = new ApiAdapterActividades().getClientService();
    }

    @Override
    public void getActividades(int codigo , int tipo , int id_direccion) {
        Call<ArrayList<Actividad>> call = serviceActividades.getActividades( id_direccion , codigo , tipo);
        call.enqueue(new Callback<ArrayList<Actividad>>() {
            @Override
            public void onResponse(Call<ArrayList<Actividad>> call, Response<ArrayList<Actividad>> response) {
                if(response.isSuccessful() && !response.body().isEmpty() && response.body().size() > 0){
                    actividadesActivityPresenter.showActividades(response.body());
                }else{
                    actividadesActivityPresenter.showMessage(strNotData);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Actividad>> call, Throwable t) {
                actividadesActivityPresenter.showMessage(t.getMessage());
            }
        });
    }

    @Override
    public void getallActividades() {
        Call<ArrayList<Actividad>> call = serviceActividades.getallActividades();
        call.enqueue(new Callback<ArrayList<Actividad>>() {
            @Override
            public void onResponse(Call<ArrayList<Actividad>> call, Response<ArrayList<Actividad>> response) {
                if(response.isSuccessful() && !response.body().isEmpty() && response.body().size() > 0){
                    actividadesActivityPresenter.showActividades(response.body());
                }else{
                    actividadesActivityPresenter.showMessage(strNotData);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Actividad>> call, Throwable t) {
                actividadesActivityPresenter.showMessage(t.getMessage());
            }
        });
    }

    @Override
    public void getComentarios(int actividad) {
        Call<ArrayList<Comentario>> call = serviceActividades.getComentarios(actividad);
        call.enqueue(new Callback<ArrayList<Comentario>>() {
            @Override
            public void onResponse(Call<ArrayList<Comentario>> call, Response<ArrayList<Comentario>> response) {
                if(response.isSuccessful() && !response.body().isEmpty() && response.body().size()>0){
                   actividadesActivityPresenter.showComentarios(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Comentario>> call, Throwable t) {

            }
        });
    }

}
