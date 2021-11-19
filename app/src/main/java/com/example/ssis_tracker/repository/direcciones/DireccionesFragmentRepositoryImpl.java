package com.example.ssis_tracker.repository.direcciones;

import com.example.ssis_tracker.api.direcciones.ApiAdapterDirecciones;
import com.example.ssis_tracker.api.direcciones.ApiServiceDirecciones;
import com.example.ssis_tracker.model.Direccion;
import com.example.ssis_tracker.presenter.direcciones.DireccionesFragmentPresenter;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DireccionesFragmentRepositoryImpl implements  DireccionesFragmentRepository {

    private DireccionesFragmentPresenter direccionesFragmentPresenter;
    private ApiAdapterDirecciones adapterDirecciones;
    private ApiServiceDirecciones serviceDirecciones;
    private String strNotData = "No se detectaron datos.";

    public DireccionesFragmentRepositoryImpl(DireccionesFragmentPresenter direccionesFragmentPresenter){
        adapterDirecciones = new ApiAdapterDirecciones();
        serviceDirecciones = adapterDirecciones.getClientService();
        this.direccionesFragmentPresenter = direccionesFragmentPresenter;
    }

    @Override
    public void getDirecciones() {
        Call<ArrayList<Direccion>> call = serviceDirecciones.getDirecciones();
        call.enqueue(new Callback<ArrayList<Direccion>>() {
            @Override
            public void onResponse(Call<ArrayList<Direccion>> call, Response<ArrayList<Direccion>> response) {
                if(response.isSuccessful() && !response.body().isEmpty()){
                    direccionesFragmentPresenter.showDirecciones(response.body());
                }else{
                    direccionesFragmentPresenter.showMessage(strNotData);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Direccion>> call, Throwable t) {
                direccionesFragmentPresenter.showMessage(t.getMessage());
            }
        });
    }
}
