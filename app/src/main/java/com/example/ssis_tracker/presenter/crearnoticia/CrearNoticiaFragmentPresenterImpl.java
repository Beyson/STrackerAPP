package com.example.ssis_tracker.presenter.crearnoticia;

import com.example.ssis_tracker.interactor.crearnoticia.CrearNoticiaFragmentInteractor;
import com.example.ssis_tracker.interactor.crearnoticia.CrearNoticiaFragmentInteractorImpl;
import com.example.ssis_tracker.view.crearnoticia.CrearNoticiasFragmentView;
import java.util.ArrayList;

public class CrearNoticiaFragmentPresenterImpl implements CrearNoticiaFragmentPresenter {

    private CrearNoticiaFragmentInteractor crearNoticiaFragmentInteractor;
    private CrearNoticiasFragmentView crearNoticiasFragmentView;

    public CrearNoticiaFragmentPresenterImpl(CrearNoticiasFragmentView crearNoticiasFragmentView){
        this.crearNoticiaFragmentInteractor = new CrearNoticiaFragmentInteractorImpl(this);
        this.crearNoticiasFragmentView = crearNoticiasFragmentView;
    }

    @Override
    public void CrearNoticia(String titulo, String descripcion, ArrayList<String[]> Imagenes) {
        this.crearNoticiaFragmentInteractor.CrearNoticia(titulo , descripcion , Imagenes);
    }

    @Override
    public void NoticiaCreada(Boolean creada, String Mensaje) {
        this.crearNoticiasFragmentView.NoticiaCreada(creada, Mensaje);
    }
}
