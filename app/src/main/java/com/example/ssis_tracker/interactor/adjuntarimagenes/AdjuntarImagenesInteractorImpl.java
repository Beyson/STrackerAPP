package com.example.ssis_tracker.interactor.adjuntarimagenes;

import android.content.Context;
import com.example.ssis_tracker.presenter.adjuntarimagenes.AdjuntarImagenesPresenter;
import com.example.ssis_tracker.repository.adjuntarimagenes.AdjuntarImagenesResposioryImpl;
import com.example.ssis_tracker.repository.adjuntarimagenes.AdjuntarImagenesRespository;

public class AdjuntarImagenesInteractorImpl implements AdjuntarImagenesInteractor {

    private AdjuntarImagenesRespository adjuntarImagenesRespository;

    public AdjuntarImagenesInteractorImpl(AdjuntarImagenesPresenter adjuntarImagenesPresenter , Context context){
        this.adjuntarImagenesRespository = new AdjuntarImagenesResposioryImpl(adjuntarImagenesPresenter , context);
    }

    @Override
    public void BuscarFolderImagenes() {
        this.adjuntarImagenesRespository.BuscarFolderImagenes();
    }

    @Override
    public void ImagenesEnCarpeta(String NombreFolder) {
        this.adjuntarImagenesRespository.ImagenesEnCarpeta(NombreFolder);
    }
}
