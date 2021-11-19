package com.example.ssis_tracker.presenter.performance;

import java.util.ArrayList;

public interface PerformanceFragmentPresenter {
    void SolicitarDatosPerformance();
    void PresentarDatosPerformance(ArrayList<Float> performances);
    void MostarMensaje(String Mensaje);
}
