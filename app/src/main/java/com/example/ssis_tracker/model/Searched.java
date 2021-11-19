package com.example.ssis_tracker.model;

public class Searched {
    private String Titulo;
    private String Descripcion;
    private int Id;
    private String Estado;
    private String Color;
    private int Tipo; //0 para Proyecto, 1 para Proceso, 2 para Actividad
    public Searched(String Titulo, String Descripcion,int id, String Estado, String Color, int Tipo){
        this.setTitulo(Titulo);
        this.setDescripcion(Descripcion);
        this.setId(id);
        this.setEstado(Estado);
        this.setColor(Color);
        this.setTipo(Tipo);
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        this.Estado = estado;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public int getTipo() {
        return Tipo;
    }

    public void setTipo(int tipo) {
        Tipo = tipo;
    }
}
