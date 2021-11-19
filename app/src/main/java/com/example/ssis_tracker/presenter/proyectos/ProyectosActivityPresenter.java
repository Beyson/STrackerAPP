package com.example.ssis_tracker.presenter.proyectos;

import com.example.ssis_tracker.model.Proyecto;
import java.util.ArrayList;

public interface ProyectosActivityPresenter {
    void getProyectos(int codigo, int tipo);
    void getallProyectos();
    void showProyectos(ArrayList<Proyecto> proyectoArrayList);
    void showMessage(String strMessage);
}
