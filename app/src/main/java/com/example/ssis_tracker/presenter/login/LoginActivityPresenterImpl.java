package com.example.ssis_tracker.presenter.login;

import com.example.ssis_tracker.interactor.login.LoginActivityInteractor;
import com.example.ssis_tracker.interactor.login.LoginActivityInteractorImpl;
import com.example.ssis_tracker.model.Login;
import com.example.ssis_tracker.view.login.LoginActivityView;

public class LoginActivityPresenterImpl implements LoginActivityPresenter {

    private LoginActivityView loginActivityView;
    private LoginActivityInteractor loginActivityInteractor;

    public LoginActivityPresenterImpl(LoginActivityView loginActivityView){
        this.loginActivityView = loginActivityView;
        this.loginActivityInteractor = new LoginActivityInteractorImpl(this);
    }

    @Override
    public void LogInUsuario(String Usuario, String Pasword, String IpAddress, double Lat, double Long) {
        this.loginActivityInteractor.LogInUsuario(Usuario , Pasword , IpAddress , Lat , Long);
    }

    @Override
    public void UsuarioLogeado(Login logins) {
        this.loginActivityView.UsuarioLogeado(logins);
    }

    @Override
    public void ShowErrosMsj(String Msj) {
        this.loginActivityView.ShowErrosMsj(Msj);
    }
}
