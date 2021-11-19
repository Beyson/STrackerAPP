package com.example.ssis_tracker.interactor.login;

import com.example.ssis_tracker.presenter.login.CambiarPasswordActivityPresenter;
import com.example.ssis_tracker.repository.login.CambiarPasswordRepository;
import com.example.ssis_tracker.repository.login.CambiarPasswordRepositoryImpl;
import com.google.gson.JsonObject;

public class CambiarPasswordActivityInteractorImpl implements CambiarPasswordActivityInteractor {

    private CambiarPasswordActivityPresenter cambiarPasswordActivityPresenter;
    private CambiarPasswordRepository        cambiarPasswordRepository;

    public CambiarPasswordActivityInteractorImpl(CambiarPasswordActivityPresenter cambiarPasswordActivityPresenter){
            this.cambiarPasswordActivityPresenter = cambiarPasswordActivityPresenter;
            this.cambiarPasswordRepository = new CambiarPasswordRepositoryImpl(this.cambiarPasswordActivityPresenter);
    }

    @Override
    public void CambiarPassword(String NuevaPassword  , String UsuarioId) {

        JsonObject jsonPassword = new JsonObject();
        jsonPassword.addProperty("id" , UsuarioId);
        jsonPassword.addProperty("password" , NuevaPassword);
        this.cambiarPasswordRepository.CambiarPassword(jsonPassword);
    }

}
