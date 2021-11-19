package com.example.ssis_tracker.presenter.direcciones;

import com.example.ssis_tracker.model.Direccion;

import java.util.ArrayList;

public interface DireccionesFragmentPresenter {
    void getDirecciones();
    void showDirecciones(ArrayList<Direccion> direccionArrayList);
    void showMessage(String strMessage);
}
