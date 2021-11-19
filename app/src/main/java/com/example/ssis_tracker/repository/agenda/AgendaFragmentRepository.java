package com.example.ssis_tracker.repository.agenda;

public interface AgendaFragmentRepository {
    void GetTemasAgendados();
    void EliminarTemasAgendados(int IdTema , int idRol);
}
