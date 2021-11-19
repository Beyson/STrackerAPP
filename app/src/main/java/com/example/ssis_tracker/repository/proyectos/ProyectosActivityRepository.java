package com.example.ssis_tracker.repository.proyectos;

import com.google.gson.JsonObject;

public interface ProyectosActivityRepository {
    void getallProyectos();
    void getProyectos(int codigo , int tipo);
    void realizarLlamado(JsonObject jsonLlamado);
}
