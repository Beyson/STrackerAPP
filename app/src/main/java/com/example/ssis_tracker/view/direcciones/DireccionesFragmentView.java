package com.example.ssis_tracker.view.direcciones;

import com.example.ssis_tracker.model.Direccion;

import java.util.ArrayList;

public interface DireccionesFragmentView {
    void getDirecciones();
    void showDirecciones(ArrayList<Direccion> direccionArrayList);
    void showMessage(String strMessage);
    void showSwipeRefreshLayout(boolean bool);
    void configAppBar(boolean bolDefault);
}
