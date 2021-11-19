package com.example.ssis_tracker.interactor.procesos;

public interface ProcesosActivityInteractor {
    void getProcesos(int codigo , int tipo, int id_direccion);
    void getallProcesos();
}
