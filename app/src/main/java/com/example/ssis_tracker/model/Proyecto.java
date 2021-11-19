package com.example.ssis_tracker.model;

import com.google.gson.annotations.SerializedName;

public class Proyecto {

    private int id;
    private String nombre;
    private String descripcion;
    private String estado;
    private String color;
    private String fechas;
    @SerializedName("porcentaje_procesos")
    private float porcentajeProcesos;
    @SerializedName("porcentaje_dias")
    private float porcentajeDias;
    @SerializedName("porcentaje_metas")
    private float porcentajeMetas;
    private int llamados;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getFechas() {
        return fechas;
    }
    public void setFechas(String fechas) {
        this.fechas = fechas;
    }
    public float getPorcentajeProcesos() {
        return porcentajeProcesos;
    }
    public void setPorcentajeProcesos(float porcentajeProcesos) {
        this.porcentajeProcesos = porcentajeProcesos;
    }
    public float getPorcentajeDias() {
        return porcentajeDias;
    }
    public void setPorcentajeDias(float porcentajeDias) {
        this.porcentajeDias = porcentajeDias;
    }
    public float getPorcentajeMetas() {
        return porcentajeMetas;
    }
    public void setPorcentajeMetas(float porcentajeMetas) {
        this.porcentajeMetas = porcentajeMetas;
    }
    public int getLlamados() {
        return llamados;
    }
    public void setLlamados(int llamados) {
        this.llamados = llamados;
    }

}
