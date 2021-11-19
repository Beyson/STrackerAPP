package com.example.ssis_tracker.presenter.login;

import com.example.ssis_tracker.interactor.login.CambiarPasswordActivityInteractor;
import com.example.ssis_tracker.interactor.login.CambiarPasswordActivityInteractorImpl;
import com.example.ssis_tracker.view.login.CambiarPasswordActivityView;

public class CambiarPasswordActivityPresenterImpl implements CambiarPasswordActivityPresenter {

    private CambiarPasswordActivityView cambiarPasswordActivityView;
    private CambiarPasswordActivityInteractor cambiarPasswordActivityInteractor;

    public CambiarPasswordActivityPresenterImpl(CambiarPasswordActivityView cambiarPasswordActivityView){
        this.cambiarPasswordActivityView = cambiarPasswordActivityView;
        this.cambiarPasswordActivityInteractor = new CambiarPasswordActivityInteractorImpl(this);
    }

    @Override
    public void CambiarPassword(String NuevaPassword , String UsuarioId) {
        this.cambiarPasswordActivityInteractor.CambiarPassword(NuevaPassword , UsuarioId);
    }

    @Override
    public void ShowError(String MensajeError) {
        this.cambiarPasswordActivityView.ShowError(MensajeError);
    }

    @Override
    public void CambioCorrecto() {
        this.cambiarPasswordActivityView.CambioCorrecto();
    }
}
