package com.example.ssis_tracker.model;


public class Agenda {

    private int id;
    private String direccion;
    private String nombre;
    private String descripcion;
    private String fecha_agendado;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getFecha_agendado() {
        return fecha_agendado;
    }
    public void setFecha_agendado(String fecha) {
        this.fecha_agendado = fecha;
    }

}
