package com.example.ssis_tracker.presenter.crearnoticia;

import java.util.ArrayList;

public interface CrearNoticiaFragmentPresenter {

    void CrearNoticia(String titulo , String descripcion , ArrayList<String[]> Imagenes);
    void NoticiaCreada(Boolean creada, String Mensaje);
}
