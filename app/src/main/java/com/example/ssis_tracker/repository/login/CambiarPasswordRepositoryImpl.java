package com.example.ssis_tracker.repository.login;

import com.example.ssis_tracker.api.login.ApiAdapterLogin;
import com.example.ssis_tracker.api.login.ApiServiceLogin;
import com.example.ssis_tracker.model.ApiResponse;
import com.example.ssis_tracker.model.Login;
import com.example.ssis_tracker.presenter.login.CambiarPasswordActivityPresenter;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CambiarPasswordRepositoryImpl implements CambiarPasswordRepository {

    private CambiarPasswordActivityPresenter cambiarPasswordActivityPresenter;
    private ApiAdapterLogin apiAdapterLogin;
    private ApiServiceLogin apiServiceLogin;

    public CambiarPasswordRepositoryImpl(CambiarPasswordActivityPresenter cambiarPasswordActivityPresenter){
        this.cambiarPasswordActivityPresenter = cambiarPasswordActivityPresenter;
        this.apiAdapterLogin = new ApiAdapterLogin();
        this.apiServiceLogin = apiAdapterLogin.getClientService();
    }

    @Override
    public void CambiarPassword(JsonObject Passwords) {
        Call<ApiResponse> Call = this.apiServiceLogin.CambiarPassword(Passwords);
        Call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(retrofit2.Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.isSuccessful()){
                    cambiarPasswordActivityPresenter.CambioCorrecto();
                }else{
                    cambiarPasswordActivityPresenter.ShowError("");
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ApiResponse> call, Throwable t) {
                cambiarPasswordActivityPresenter.ShowError(t.getMessage());
            }
        });
    }
}
