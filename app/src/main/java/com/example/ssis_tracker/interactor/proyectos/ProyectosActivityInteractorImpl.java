package com.example.ssis_tracker.interactor.proyectos;

import com.example.ssis_tracker.presenter.llamadosatencion.LlamadosAtencionPresenter;
import com.example.ssis_tracker.presenter.proyectos.ProyectosActivityPresenter;
import com.example.ssis_tracker.repository.proyectos.ProyectosActivityRepository;
import com.example.ssis_tracker.repository.proyectos.ProyectosActivityRepositoryImpl;

public class ProyectosActivityInteractorImpl implements ProyectosActivityInteractor {

    private ProyectosActivityRepository proyectosActivityRepository;

    public ProyectosActivityInteractorImpl(ProyectosActivityPresenter proyectosActivityPresenter , LlamadosAtencionPresenter llamadosAtencionPresenter){
        proyectosActivityRepository = new ProyectosActivityRepositoryImpl(proyectosActivityPresenter, llamadosAtencionPresenter);
    }

    @Override
    public void getProyectos(int codigo, int tipo) {
        proyectosActivityRepository.getProyectos(codigo , tipo);
    }

    @Override
    public void getallProyectos() {
        proyectosActivityRepository.getallProyectos();
    }

}
