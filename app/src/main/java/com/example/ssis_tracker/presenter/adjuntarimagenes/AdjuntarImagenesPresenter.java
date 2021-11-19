package com.example.ssis_tracker.presenter.adjuntarimagenes;

import java.util.ArrayList;

public interface AdjuntarImagenesPresenter {
    void BuscarFolderImagenes();
    void MostarFolderImagenes(ArrayList<String[]> FolderImagenes);
    void BuscarImagenesEnCarpeta(String NombreFolder);
    void MostarImagenes(ArrayList<String[]> Imagenes);
}
