package com.example.ssis_tracker.interactor.agenda;

public interface AgendaFragmentInteractor {
    void GetTemasAgendados();
    void EliminarTemasAgendados(int IdTema, int idRol);
}
