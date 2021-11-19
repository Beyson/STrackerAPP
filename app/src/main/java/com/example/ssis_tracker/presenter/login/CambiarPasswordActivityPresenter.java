package com.example.ssis_tracker.presenter.login;

public interface CambiarPasswordActivityPresenter {
    void CambiarPassword(String NuevaPassword , String UsuarioId);
    void ShowError(String MenssajeError);
    void CambioCorrecto();
}
