package com.example.ssis_tracker.presenter.direcciones;

import com.example.ssis_tracker.interactor.direcciones.DireccionesFragmentInteractor;
import com.example.ssis_tracker.interactor.direcciones.DireccionesFragmentInteractorImpl;
import com.example.ssis_tracker.model.Direccion;
import com.example.ssis_tracker.view.direcciones.DireccionesFragmentView;
import java.util.ArrayList;

public class DireccionesFragmentPresenterImpl implements DireccionesFragmentPresenter {

    private DireccionesFragmentView direccionesFragmentView;
    private DireccionesFragmentInteractor direccionesFragmentInteractor;

    public DireccionesFragmentPresenterImpl(DireccionesFragmentView direccionesFragmentView){
        this.direccionesFragmentView = direccionesFragmentView;
        direccionesFragmentInteractor = new DireccionesFragmentInteractorImpl(this);
    }

    @Override
    public void getDirecciones() {
        direccionesFragmentInteractor.getDirecciones();
    }

    @Override
    public void showDirecciones(ArrayList<Direccion> direccionArrayList) {
        direccionesFragmentView.showDirecciones(direccionArrayList);
    }

    @Override
    public void showMessage(String strMessage) {
        direccionesFragmentView.showMessage(strMessage);
    }
}
