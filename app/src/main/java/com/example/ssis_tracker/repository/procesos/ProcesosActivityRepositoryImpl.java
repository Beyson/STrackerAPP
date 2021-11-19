package com.example.ssis_tracker.repository.procesos;

import com.example.ssis_tracker.api.procesos.ApiAdapterProcesos;
import com.example.ssis_tracker.api.procesos.ApiServiceProcesos;
import com.example.ssis_tracker.model.Proceso;
import com.example.ssis_tracker.presenter.procesos.ProcesosActivityPresenter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProcesosActivityRepositoryImpl implements  ProcesosActivityRepository {

    private ProcesosActivityPresenter procesosActivityPresenter;
    private ApiAdapterProcesos adapterProcesos;
    private ApiServiceProcesos serviceProcesos;
    private String strNotData = "No se detectaron datos.";

    public ProcesosActivityRepositoryImpl(ProcesosActivityPresenter procesosActivityPresenter) {
        this.procesosActivityPresenter = procesosActivityPresenter;
        adapterProcesos = new ApiAdapterProcesos();
        serviceProcesos = adapterProcesos.getClientService();
    }

    @Override
    public void getProcesos(int codigo , int tipo , int id_direccion) {
        Call<ArrayList<Proceso>> call = this.serviceProcesos.getProcesos(id_direccion,codigo , tipo);
        call.enqueue(new Callback<ArrayList<Proceso>>() {
            @Override
            public void onResponse(Call<ArrayList<Proceso>> call, Response<ArrayList<Proceso>> response) {
                if(response.isSuccessful() && !response.body().isEmpty()){
                    procesosActivityPresenter.showProcesos(response.body());
                }else{
                    procesosActivityPresenter.showMessage(strNotData);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Proceso>> call, Throwable t) {
                procesosActivityPresenter.showMessage(t.getMessage());
            }
        });
    }
    @Override
    public void getallProcesos() {
        Call<ArrayList<Proceso>> call = this.serviceProcesos.getallProcesos();
        call.enqueue(new Callback<ArrayList<Proceso>>() {
            @Override
            public void onResponse(Call<ArrayList<Proceso>> call, Response<ArrayList<Proceso>> response) {
                if(response.isSuccessful() && !response.body().isEmpty()){
                    procesosActivityPresenter.showProcesos(response.body());
                }else{
                    procesosActivityPresenter.showMessage(strNotData);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Proceso>> call, Throwable t) {
                procesosActivityPresenter.showMessage(t.getMessage());
            }
        });
    }
}
