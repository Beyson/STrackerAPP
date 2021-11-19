package com.example.ssis_tracker.view.proyectos;

import com.example.ssis_tracker.model.Proyecto;
import java.util.ArrayList;

public interface ProyectosActivityView {
    void getProyectos(int codigo ,  int tipo);
    void getallProyectos();
    void showProyectos(ArrayList<Proyecto> proyectoArrayList);
    void showMessage(String strMessage);
    void showSwipeRefreshLayout(boolean bool);

    void configAppBar(boolean bolDefault);
}
