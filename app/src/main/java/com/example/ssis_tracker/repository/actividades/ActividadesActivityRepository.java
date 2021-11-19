package com.example.ssis_tracker.repository.actividades;

public interface ActividadesActivityRepository {
    void getActividades(int codigo , int tipo , int id_direccion);
    void getallActividades();
    void getComentarios(int actividad);
}
