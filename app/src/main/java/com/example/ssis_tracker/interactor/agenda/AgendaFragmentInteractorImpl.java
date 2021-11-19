package com.example.ssis_tracker.interactor.agenda;

import com.example.ssis_tracker.presenter.agenda.AgendaFragmentPresenter;
import com.example.ssis_tracker.repository.agenda.AgendaFragmentRepository;
import com.example.ssis_tracker.repository.agenda.AgendaFragmentRepositoryImpl;

public class AgendaFragmentInteractorImpl implements AgendaFragmentInteractor {

    private AgendaFragmentRepository agendaFragmentRepository;

    public AgendaFragmentInteractorImpl(AgendaFragmentPresenter agendaFragmentPresenter){
        this.agendaFragmentRepository = new AgendaFragmentRepositoryImpl(agendaFragmentPresenter);
    }

    @Override
    public void GetTemasAgendados() {
        this.agendaFragmentRepository.GetTemasAgendados();
    }

    @Override
    public void EliminarTemasAgendados(int IdTema,int idRol) {
        this.agendaFragmentRepository.EliminarTemasAgendados(IdTema,idRol);
    }
}
