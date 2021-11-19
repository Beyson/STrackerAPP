package com.example.ssis_tracker.repository.login;

import com.example.ssis_tracker.api.login.ApiAdapterLogin;
import com.example.ssis_tracker.api.login.ApiServiceLogin;
import com.example.ssis_tracker.model.Login;
import com.example.ssis_tracker.presenter.login.LoginActivityPresenter;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityRepositoryImpl implements LoginActivityRepository {

    private LoginActivityPresenter loginActivityPresenter;
    private ApiAdapterLogin apiAdapterLogin;
    private ApiServiceLogin apiServiceLogin;

    public  LoginActivityRepositoryImpl(LoginActivityPresenter loginActivityPresenter){
        this.loginActivityPresenter = loginActivityPresenter;
        this.apiAdapterLogin = new ApiAdapterLogin();
        this.apiServiceLogin = apiAdapterLogin.getClientService();
    }

    @Override
    public void LogInUsuario(JsonObject LoginJson) {
        Call<Login> call = this.apiServiceLogin.getLogin(LoginJson);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if(response.isSuccessful()){
                    if(response.body().getId_usuario() != 0){
                        loginActivityPresenter.UsuarioLogeado(response.body());
                    }else{
                        loginActivityPresenter.ShowErrosMsj("Las credenciales son incorrectas");
                    }

                }else{
                    loginActivityPresenter.ShowErrosMsj("Las credenciales son incorrectas");
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                loginActivityPresenter.ShowErrosMsj(t.getMessage());
            }
        });
    }
}
