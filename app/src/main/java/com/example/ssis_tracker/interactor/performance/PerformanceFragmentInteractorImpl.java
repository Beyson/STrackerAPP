package com.example.ssis_tracker.interactor.performance;

import com.example.ssis_tracker.presenter.performance.PerformanceFragmentPresenter;
import com.example.ssis_tracker.repository.performance.PerformanceFragmentRepository;
import com.example.ssis_tracker.repository.performance.PerformanceFragmentRepositoryImpl;

public class PerformanceFragmentInteractorImpl implements PerformanceFragmentInteractor {

    private PerformanceFragmentPresenter performanceFragmentPresenter;
    private PerformanceFragmentRepository performanceFragmentRepository;

    public PerformanceFragmentInteractorImpl(PerformanceFragmentPresenter performanceFragmentPresenter){
        this.performanceFragmentPresenter = performanceFragmentPresenter;
        this.performanceFragmentRepository = new PerformanceFragmentRepositoryImpl(this.performanceFragmentPresenter);
    }

    @Override
    public void SolicitarDatosPerformance() {
        this.performanceFragmentRepository.SolicitarDatosPerformance();
    }
}
