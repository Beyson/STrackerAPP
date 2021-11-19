package com.example.ssis_tracker.repository.procesos;


public interface ProcesosActivityRepository {
    void getProcesos( int codigo , int tipo , int id_direccion );
    void getallProcesos();
}
