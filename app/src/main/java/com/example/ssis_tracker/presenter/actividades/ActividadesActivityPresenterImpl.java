package com.example.ssis_tracker.presenter.actividades;

import com.example.ssis_tracker.interactor.actividades.ActividadesActivityInteractor;
import com.example.ssis_tracker.interactor.actividades.ActividadesActivityInteractorImpl;
import com.example.ssis_tracker.model.Actividad;
import com.example.ssis_tracker.model.Comentario;
import com.example.ssis_tracker.view.actividades.ActividadesActivityView;
import com.example.ssis_tracker.view.actividades.ActividadesFragmentView;
import java.util.ArrayList;

public class ActividadesActivityPresenterImpl implements ActividadesActivityPresenter{

    private ActividadesFragmentView actividadesFragmentView;
    private ActividadesActivityView actividadesActivityView;
    private ActividadesActivityInteractor actividadesActivityInteractor;

    public ActividadesActivityPresenterImpl(ActividadesActivityView actividadesActivityView , ActividadesFragmentView actividadesFragmentView) {
        this.actividadesActivityView  = actividadesActivityView;
        this.actividadesActivityInteractor = new ActividadesActivityInteractorImpl(this);
        this.actividadesFragmentView  = actividadesFragmentView;
    }

    @Override
    public void getActividades(int codigo ,  int tipo , int id_direccion) {
        this.actividadesActivityInteractor.getActividades(codigo, tipo , id_direccion);
    }

    @Override
    public void getallActividades() {
        this.actividadesActivityInteractor.getallActividades();
    }

    @Override
    public void showActividades(ArrayList<Actividad> actividadArrayList) {
        this.actividadesActivityView.showActividades(actividadArrayList);
    }

    @Override
    public void showMessage(String strMessage) {
        this.actividadesActivityView.showMessage(strMessage);
    }

    @Override
    public void getComentarios(int actividad) {
        this.actividadesActivityInteractor.getComentarios(actividad);
    }

    @Override
    public void showComentarios(ArrayList<Comentario> comentarios) {
        this.actividadesFragmentView.showComentarios(comentarios);
    }

}
