package com.example.ssis_tracker.presenter.agenda;

import com.example.ssis_tracker.interactor.agenda.AgendaFragmentInteractor;
import com.example.ssis_tracker.interactor.agenda.AgendaFragmentInteractorImpl;
import com.example.ssis_tracker.model.Agenda;
import com.example.ssis_tracker.view.agenda.AgendaFragmentView;
import java.util.ArrayList;

public class AgendaFragmentPresenterImpl implements AgendaFragmentPresenter {

    private AgendaFragmentView agendaFragmentView;
    private AgendaFragmentInteractor agendaFragmentInteractor;

    public AgendaFragmentPresenterImpl(AgendaFragmentView agendaFragmentView){
        this.agendaFragmentView = agendaFragmentView;
        this.agendaFragmentInteractor = new AgendaFragmentInteractorImpl(this);
    }


    @Override
    public void GetTemasAgendados() {
        this.agendaFragmentInteractor.GetTemasAgendados();
    }

    @Override
    public void ListarTemasAgendados(ArrayList<Agenda> TemasAgendados) {
        this.agendaFragmentView.ListarTemasAgendados(TemasAgendados);
    }

    @Override
    public void EliminarTemasAgendados(int IdTema,int idRol) {
        this.agendaFragmentInteractor.EliminarTemasAgendados(IdTema,idRol);
    }

    @Override
    public void ErrorListarTemas(String TxtError) {
        this.agendaFragmentView.ErrorListarTemas(TxtError);
    }


}
