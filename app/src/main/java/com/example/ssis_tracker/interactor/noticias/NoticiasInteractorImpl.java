package com.example.ssis_tracker.interactor.noticias;

import com.example.ssis_tracker.presenter.noticias.FragmentNoticiasPresenter;
import com.example.ssis_tracker.repository.noticias.FragmentNoticiasRepository;
import com.example.ssis_tracker.repository.noticias.FragmentNoticiasRepositoryImpl;

public class NoticiasInteractorImpl implements NoticiasInteractor {

    private FragmentNoticiasRepository fragmentNoticiasRepository;
    public NoticiasInteractorImpl(FragmentNoticiasPresenter noticiasPresenter){
            this.fragmentNoticiasRepository = new FragmentNoticiasRepositoryImpl(noticiasPresenter);
    }

    @Override
    public void SolicitarNoticias() {

    }
}
