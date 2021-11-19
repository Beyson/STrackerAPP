package com.example.ssis_tracker.presenter.agregarcomentarios;

public interface ActivityComentarPresenter {
    void AgregarComentario(String Usuario , String Comentario, int Actividad);
    void ComentarioAgregado(int Repuesta);
}
