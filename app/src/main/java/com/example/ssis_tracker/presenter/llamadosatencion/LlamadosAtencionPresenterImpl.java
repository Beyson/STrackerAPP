package com.example.ssis_tracker.presenter.llamadosatencion;

import com.example.ssis_tracker.interactor.llamadosatencion.LlamadosAtencionInteractor;
import com.example.ssis_tracker.interactor.llamadosatencion.LlamadosAtencionInteractorImpl;
import com.example.ssis_tracker.view.LlamadosAtencion.LlamadosAtencionView;

public class LlamadosAtencionPresenterImpl implements LlamadosAtencionPresenter {

    private LlamadosAtencionInteractor llamadosAtencionInteractor;
    private LlamadosAtencionView       llamadosAtencionView;

    public LlamadosAtencionPresenterImpl(LlamadosAtencionView llamadosAtencionView){
        this.llamadosAtencionInteractor = new LlamadosAtencionInteractorImpl(this);
        this.llamadosAtencionView       = llamadosAtencionView;
    }

    @Override
    public void EnviarLlamadosdeAtencion(String proyecto , String usuario , String rol) {
        this.llamadosAtencionInteractor.EnviarLlamadosdeAtencion(proyecto , usuario , rol);
    }

    @Override
    public void ShowLlamadosdeAtencion(String Llamados) {
        this.llamadosAtencionView.ShowLlamadosdeAtencion(Llamados);
    }
}
