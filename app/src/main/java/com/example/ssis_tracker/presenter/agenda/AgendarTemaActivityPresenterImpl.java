package com.example.ssis_tracker.presenter.agenda;

import com.example.ssis_tracker.interactor.agenda.AgendarTemaActitvityInteractor;
import com.example.ssis_tracker.interactor.agenda.AgendarTemaActivityInteractorImpl;
import com.example.ssis_tracker.view.agenda.AgendarTemaActivityView;

public class AgendarTemaActivityPresenterImpl implements AgendarTemaActivityPresenter {

    private AgendarTemaActivityView agendarTemaActivityView;
    private AgendarTemaActitvityInteractor agendarTemaActitvityInteractor;

    public AgendarTemaActivityPresenterImpl(AgendarTemaActivityView agendarTemaActivityView){
        this.agendarTemaActivityView        = agendarTemaActivityView;
        this.agendarTemaActitvityInteractor = new AgendarTemaActivityInteractorImpl(this);
    }

    @Override
    public void AgendarTema(String tema, int usuario, String Proyecto , String Comentario) {
        this.agendarTemaActitvityInteractor.AgendarTema(tema, usuario, Proyecto , Comentario);
    }

    @Override
    public void TemaRegistrado(boolean registrado) {
        this.agendarTemaActivityView.TemaRegistrado(registrado);
    }
}
