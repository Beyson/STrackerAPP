package com.example.ssis_tracker.view.agenda;

import com.example.ssis_tracker.model.Agenda;
import java.util.ArrayList;

public interface AgendaFragmentView {
    void configAppBar(boolean bolDefault);
    void GetTemasAgendados();
    void ListarTemasAgendados(ArrayList<Agenda> TemasAgendados);
    void EliminarTemasAgendados(int IdTema, int idRol);
    void ErrorListarTemas(String TxtError);
}
