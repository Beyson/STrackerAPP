package com.example.ssis_tracker.interactor.llamadosatencion;

import com.example.ssis_tracker.presenter.llamadosatencion.LlamadosAtencionPresenter;
import com.example.ssis_tracker.repository.proyectos.ProyectosActivityRepository;
import com.example.ssis_tracker.repository.proyectos.ProyectosActivityRepositoryImpl;
import com.google.gson.JsonObject;

public class LlamadosAtencionInteractorImpl implements LlamadosAtencionInteractor {

    private ProyectosActivityRepository proyectosActivityRepository;

    public LlamadosAtencionInteractorImpl(LlamadosAtencionPresenter llamadosAtencionPresenter){
        this.proyectosActivityRepository = new ProyectosActivityRepositoryImpl(null , llamadosAtencionPresenter);
    }

    @Override
    public void EnviarLlamadosdeAtencion(String proyecto , String usuario , String rol) {
        JsonObject Llamados = new JsonObject();
        Llamados.addProperty("proyecto" , proyecto);
        Llamados.addProperty("usuario"  , usuario);
        Llamados.addProperty("rol"      , rol);
        this.proyectosActivityRepository.realizarLlamado(Llamados);
    }
}
