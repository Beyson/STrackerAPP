package com.example.ssis_tracker.presenter.adjuntarimagenes;

import android.content.Context;

import com.example.ssis_tracker.interactor.adjuntarimagenes.AdjuntarImagenesInteractor;
import com.example.ssis_tracker.interactor.adjuntarimagenes.AdjuntarImagenesInteractorImpl;
import com.example.ssis_tracker.view.adjuntarimagenes.AdjuntarImagenesView;
import java.util.ArrayList;

public class AdjuntarImagenesPresenterImpl implements AdjuntarImagenesPresenter {

    private AdjuntarImagenesView adjuntarImagenesView;
    private AdjuntarImagenesInteractor adjuntarImagenesInteractor;

    public AdjuntarImagenesPresenterImpl(AdjuntarImagenesView adjuntarImagenesView , Context context){
        this.adjuntarImagenesView = adjuntarImagenesView;
        this.adjuntarImagenesInteractor = new AdjuntarImagenesInteractorImpl(this , context);
    }

    @Override
    public void BuscarFolderImagenes() {
        this.adjuntarImagenesInteractor.BuscarFolderImagenes();
    }

    @Override
    public void MostarFolderImagenes(ArrayList<String[]> FolderImagenes) {
        this.adjuntarImagenesView.MostarFolderImagenes(FolderImagenes);
    }

    @Override
    public void BuscarImagenesEnCarpeta(String NombreFolder) {
        this.adjuntarImagenesInteractor.ImagenesEnCarpeta(NombreFolder);
    }

    @Override
    public void MostarImagenes(ArrayList<String[]> Imagenes) {
        this.adjuntarImagenesView.MostarImagenes(Imagenes);
    }
}
