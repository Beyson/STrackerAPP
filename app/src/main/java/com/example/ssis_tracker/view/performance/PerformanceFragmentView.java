package com.example.ssis_tracker.view.performance;

import java.util.ArrayList;

public interface PerformanceFragmentView {
    void SolicitarDatosPerformance();
    void PresentarDatosPerformance(ArrayList<Float> performances);
    void MostarMensaje(String mensaje);
}
