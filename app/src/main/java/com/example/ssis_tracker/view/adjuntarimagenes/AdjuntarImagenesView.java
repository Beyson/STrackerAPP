package com.example.ssis_tracker.view.adjuntarimagenes;

import java.util.ArrayList;

public interface AdjuntarImagenesView {

    void BuscarFolderImagenes();
    void MostarFolderImagenes(ArrayList<String[]> FolderImagenes);
    void MostarImagenes(ArrayList<String[]> Imagenes);
    void BuscarImagenes(String NombreFolder);
}
