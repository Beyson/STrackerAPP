package com.example.ssis_tracker.interactor.procesos;


import com.example.ssis_tracker.presenter.procesos.ProcesosActivityPresenter;
import com.example.ssis_tracker.repository.procesos.ProcesosActivityRepository;
import com.example.ssis_tracker.repository.procesos.ProcesosActivityRepositoryImpl;

public class ProcesosActivityInteractorImpl implements ProcesosActivityInteractor {
    private ProcesosActivityRepository procesosActivityRepository;

    public ProcesosActivityInteractorImpl(ProcesosActivityPresenter procesosActivityPresenter) {
        procesosActivityRepository = new ProcesosActivityRepositoryImpl(procesosActivityPresenter);
    }

    @Override
    public void getProcesos(int codigo , int tipo, int id_direccion) {
        procesosActivityRepository.getProcesos(codigo , tipo , id_direccion);
    }

    @Override
    public void getallProcesos() {
        procesosActivityRepository.getallProcesos();
    }
}
