package com.example.ssis_tracker.presenter.agenda;

public interface AgendarTemaActivityPresenter {
    void AgendarTema(String tema, int usuario,  String Proyecto , String Comentario);
    void TemaRegistrado(boolean registrado);
}
