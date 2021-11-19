package com.example.ssis_tracker.presenter.noticias;

import com.example.ssis_tracker.interactor.noticias.NoticiasInteractor;
import com.example.ssis_tracker.interactor.noticias.NoticiasInteractorImpl;
import com.example.ssis_tracker.view.notificaciones.NoticiasFragmentView;

public class FragmentNoticiasPresenterImpl implements FragmentNoticiasPresenter {

    private NoticiasFragmentView noticiasFragmentView;
    private NoticiasInteractor   noticiasInteractor;

    public FragmentNoticiasPresenterImpl(NoticiasFragmentView noticiasFragmentView){
        this.noticiasFragmentView = noticiasFragmentView;
        this.noticiasInteractor = new NoticiasInteractorImpl(this);
    }

    @Override
    public void SolicitarNoticias() {

    }

    @Override
    public void MostarNoticias() {

    }
}
