package com.example.ssis_tracker.view.crearnoticia;

import java.util.ArrayList;

public interface CrearNoticiasFragmentView {

    void RegistrarNoticia(String titulo , String Noticia , ArrayList<String[]> Imagenes);
    void NoticiaCreada(Boolean creada, String Mensaje);
}
