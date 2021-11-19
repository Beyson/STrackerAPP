package com.example.ssis_tracker.view.actividades;

import com.example.ssis_tracker.model.Actividad;
import java.util.ArrayList;

public interface ActividadesActivityView {

    void getActividades(int codigo, int tipo , int id_direccion);
    void getallActividades();
    void showActividades(ArrayList<Actividad> actividadArrayList);
    void showMessage(String strMessage);
    void showSwipeRefreshLayout(boolean bool);

}
