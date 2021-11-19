package com.example.ssis_tracker.repository.performance;

import com.example.ssis_tracker.api.performance.ApiAdapterPerformance;
import com.example.ssis_tracker.api.performance.ApiServicePerformance;
import com.example.ssis_tracker.model.ApiResponse;
import com.example.ssis_tracker.presenter.performance.PerformanceFragmentPresenter;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerformanceFragmentRepositoryImpl implements PerformanceFragmentRepository {

    private PerformanceFragmentPresenter performanceFragmentPresenter;
    private ApiAdapterPerformance adapterPerformance;
    private ApiServicePerformance servicePerformance;
    private ArrayList<Float> arrayListPerformances;

    public PerformanceFragmentRepositoryImpl(PerformanceFragmentPresenter performanceFragmentPresenter){
         this.performanceFragmentPresenter = performanceFragmentPresenter;
         this.adapterPerformance = new ApiAdapterPerformance();
         this.servicePerformance = adapterPerformance.getClientService();
         this.arrayListPerformances = new ArrayList<>();
    }

    @Override
    public void SolicitarDatosPerformance() {
        PerformanceRegistrados();
    }

    private void PerformanceRegistrados(){
        Call<ApiResponse> call = servicePerformance.registrados();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.isSuccessful() && response.body().getState().equals("1")){
                    arrayListPerformances.add( Float.parseFloat( response.body().getDescription()) );
                    PerformanceFinalizados();
                }else{
                    arrayListPerformances.add(0.0f);
                    performanceFragmentPresenter.MostarMensaje("error al cargar los procesos registrados");
                }
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                arrayListPerformances.add(0.0f);
                performanceFragmentPresenter.MostarMensaje(t.getMessage());
            }
        });
    }

    private void PerformanceFinalizados(){
        Call<ApiResponse> call = servicePerformance.procesosfinalizados();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.isSuccessful() && response.body().getState().equals("1")){
                    arrayListPerformances.add( Float.parseFloat( response.body().getDescription()) );
                    PerformanceEficiencia();
                }else{
                    arrayListPerformances.add(0.0f);
                    performanceFragmentPresenter.MostarMensaje("error al cargar los procesos terminados");
                }
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                arrayListPerformances.add(0.0f);
                performanceFragmentPresenter.MostarMensaje(t.getMessage());
            }
        });
    }

    private void PerformanceEficiencia(){
        Call<ApiResponse> call = servicePerformance.eficiencia();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.isSuccessful() && response.body().getState().equals("1")){
                    arrayListPerformances.add( Float.parseFloat( response.body().getDescription()) );
                    PerformanceTerminadoATiempo();
                }else{
                    arrayListPerformances.add(0.0f);
                    performanceFragmentPresenter.MostarMensaje("error al cargar la eficiencia");
                }
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                arrayListPerformances.add(0.0f);
                performanceFragmentPresenter.MostarMensaje(t.getMessage());
            }
        });
    }

    private void PerformanceTerminadoATiempo(){
        Call<ApiResponse> call = servicePerformance.procesosterminadotiempo();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.isSuccessful() && response.body().getState().equals("1")){
                    arrayListPerformances.add( Float.parseFloat( response.body().getDescription()) );
                    PerformanceAtrasados();
                }else{
                    arrayListPerformances.add(0.0f);
                    performanceFragmentPresenter.MostarMensaje("error al cargar los procesos terminados a tiempo");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                arrayListPerformances.add(0.0f);
                performanceFragmentPresenter.MostarMensaje(t.getMessage());
            }
        });
    }

    private void PerformanceAtrasados(){
        Call<ApiResponse> call = servicePerformance.procesosatrasado();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.isSuccessful() && response.body().getState().equals("1")){
                    arrayListPerformances.add( Float.parseFloat( response.body().getDescription()) );
                    performanceFragmentPresenter.PresentarDatosPerformance(arrayListPerformances);
                }else{
                    arrayListPerformances.add(0.0f);
                    performanceFragmentPresenter.MostarMensaje("error al cargar los procesos atrasados");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                arrayListPerformances.add(0.0f);
                performanceFragmentPresenter.MostarMensaje(t.getMessage());
            }
        });
    }

}
