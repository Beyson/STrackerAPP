package com.example.ssis_tracker.interactor.actividades;

public interface ActividadesActivityInteractor {
    void getActividades(int codigo , int tipo, int id_direccion);
    void getallActividades();
    void getComentarios(int processo);
}
