package com.example.ssis_tracker.interactor.crearnoticia;

import com.example.ssis_tracker.presenter.crearnoticia.CrearNoticiaFragmentPresenter;
import com.example.ssis_tracker.repository.crearnoticia.CrearNoticiaFragmentRepositoty;
import com.example.ssis_tracker.repository.crearnoticia.CrearNoticiaFragmentRepositotyImpl;
import java.util.ArrayList;

public class CrearNoticiaFragmentInteractorImpl implements CrearNoticiaFragmentInteractor {

    private CrearNoticiaFragmentRepositoty crearNoticiaFragmentRepositoty;
    public CrearNoticiaFragmentInteractorImpl(CrearNoticiaFragmentPresenter crearNoticiaFragmentPresenter){
        this.crearNoticiaFragmentRepositoty = new CrearNoticiaFragmentRepositotyImpl(crearNoticiaFragmentPresenter);
    }

    @Override
    public void CrearNoticia(String titulo, String noticia, ArrayList<String[]> Imagenes) {
        this.crearNoticiaFragmentRepositoty.CrearNoticia(titulo , noticia , Imagenes);
    }
}
