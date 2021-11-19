package com.example.ssis_tracker.interactor.login;

import com.example.ssis_tracker.presenter.login.LoginActivityPresenter;
import com.example.ssis_tracker.repository.login.LoginActivityRepository;
import com.example.ssis_tracker.repository.login.LoginActivityRepositoryImpl;
import com.google.gson.JsonObject;

public class LoginActivityInteractorImpl implements LoginActivityInteractor {

    private LoginActivityPresenter loginActivityPresenter;
    private LoginActivityRepository loginActivityRepository;

    public LoginActivityInteractorImpl(LoginActivityPresenter loginActivityPresenter){
        this.loginActivityPresenter = loginActivityPresenter;
        this.loginActivityRepository = new LoginActivityRepositoryImpl(this.loginActivityPresenter);
    }

    @Override
    public void LogInUsuario(String Usuario, String Pasword, String IpAddress, double Lat, double Long) {
        JsonObject LoginJson = new JsonObject();
        LoginJson.addProperty("user"        ,Usuario);
        LoginJson.addProperty("password"    ,Pasword);
        LoginJson.addProperty("plataforma"  ,String.valueOf(1));
        LoginJson.addProperty("ip"          ,IpAddress);
        LoginJson.addProperty("latitud"     ,String.valueOf( Lat ));
        LoginJson.addProperty("longitud"    ,String.valueOf( Long ));

        this.loginActivityRepository.LogInUsuario(LoginJson);
    }

}
