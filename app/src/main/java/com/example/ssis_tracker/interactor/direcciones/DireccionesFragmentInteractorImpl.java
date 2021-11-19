package com.example.ssis_tracker.interactor.direcciones;

import com.example.ssis_tracker.presenter.direcciones.DireccionesFragmentPresenter;
import com.example.ssis_tracker.repository.direcciones.DireccionesFragmentRepository;
import com.example.ssis_tracker.repository.direcciones.DireccionesFragmentRepositoryImpl;

public class DireccionesFragmentInteractorImpl implements DireccionesFragmentInteractor {

    private DireccionesFragmentPresenter direccionesFragmentPresenter;
    private DireccionesFragmentRepository direccionesFragmentRepository;

    public DireccionesFragmentInteractorImpl(DireccionesFragmentPresenter direccionesFragmentPresenter){
        this.direccionesFragmentPresenter = direccionesFragmentPresenter;
        direccionesFragmentRepository = new DireccionesFragmentRepositoryImpl(this.direccionesFragmentPresenter);
    }

    @Override
    public void getDirecciones() {
        direccionesFragmentRepository.getDirecciones();
    }
}
