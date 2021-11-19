package com.example.ssis_tracker.presenter.performance;

import com.example.ssis_tracker.interactor.performance.PerformanceFragmentInteractor;
import com.example.ssis_tracker.interactor.performance.PerformanceFragmentInteractorImpl;
import com.example.ssis_tracker.view.performance.PerformanceFragmentView;
import java.util.ArrayList;

public class PerformanceFragmentPresenterImpl implements PerformanceFragmentPresenter {

    private PerformanceFragmentView performanceFragmentView;
    private PerformanceFragmentInteractor performanceFragmentInteractor;

    public PerformanceFragmentPresenterImpl(PerformanceFragmentView performanceFragmentView){
        this.performanceFragmentView = performanceFragmentView;
        this.performanceFragmentInteractor = new PerformanceFragmentInteractorImpl(this);
    }

    @Override
    public void SolicitarDatosPerformance() {
        this.performanceFragmentInteractor.SolicitarDatosPerformance();
    }

    @Override
    public void PresentarDatosPerformance(ArrayList<Float> performances) {
        this.performanceFragmentView.PresentarDatosPerformance(performances);
    }

    @Override
    public void MostarMensaje(String Mensaje) {

    }
}
