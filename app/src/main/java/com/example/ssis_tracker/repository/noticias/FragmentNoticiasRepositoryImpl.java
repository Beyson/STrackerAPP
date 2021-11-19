package com.example.ssis_tracker.repository.noticias;

import com.example.ssis_tracker.presenter.noticias.FragmentNoticiasPresenter;

public class FragmentNoticiasRepositoryImpl implements FragmentNoticiasRepository {

    private FragmentNoticiasPresenter fragmentNoticiasPresenter;
    public FragmentNoticiasRepositoryImpl(FragmentNoticiasPresenter fragmentNoticiasPresenter){
        this.fragmentNoticiasPresenter = fragmentNoticiasPresenter;
    }

    @Override
    public void SolicitarNoticias() {

    }
}
