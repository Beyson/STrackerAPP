package com.example.ssis_tracker.model;

public class ActividadesDependientes {

    private String actividad;
    private String unidad_asignada;
    private String proceso;
    private int porcentaje_ejecutado;

    public String getActividad() {
        return actividad;
    }
    public void setActividad(String actividad) {
        this.actividad = actividad;
    }
    public String getUnidad_asignada() {
        return unidad_asignada;
    }
    public void setUnidad_asignada(String unidad_asignada) {
        this.unidad_asignada = unidad_asignada;
    }
    public String getProceso() {
        return proceso;
    }
    public void setProceso(String proceso) {
        this.proceso = proceso;
    }
    public int getPorcentaje_ejecutado() {
        return porcentaje_ejecutado;
    }
    public void setPorcentaje_ejecutado(int porcentaje_ejecutado) {
        this.porcentaje_ejecutado = porcentaje_ejecutado;
    }

}
