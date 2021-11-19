package com.example.ssis_tracker.presenter.login;

import com.example.ssis_tracker.model.Login;

import java.util.ArrayList;

public interface LoginActivityPresenter {
    void LogInUsuario(String Usuario , String Pasword , String IpAddress , double Lat , double Long);
    void UsuarioLogeado(Login logins);
    void ShowErrosMsj(String Msj);
}
