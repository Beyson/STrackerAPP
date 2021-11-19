package com.example.ssis_tracker.view.login;

import com.example.ssis_tracker.model.Login;

import java.util.ArrayList;

public interface LoginActivityView {
    void LogInUsuario();
    void UsuarioLogeado(Login Login);
    void ShowErrosMsj(String Msj);
}
