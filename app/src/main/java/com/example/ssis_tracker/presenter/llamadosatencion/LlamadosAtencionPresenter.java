package com.example.ssis_tracker.presenter.llamadosatencion;

public interface LlamadosAtencionPresenter {
    void EnviarLlamadosdeAtencion(String proyecto , String usuario , String rol);
    void ShowLlamadosdeAtencion(String Llamados);
}
