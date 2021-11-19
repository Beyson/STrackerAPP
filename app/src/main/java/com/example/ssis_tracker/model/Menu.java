package com.example.ssis_tracker.model;

import java.util.ArrayList;

public class Menu {

    private int id_opcion;
    private String id;
    private String opcion;
    private ArrayList<MenuSubOpciones> sub_opciones;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getId_opcion() {
        return id_opcion;
    }
    public void setId_opcion(int id_opcion) {
        this.id_opcion = id_opcion;
    }
    public String getOpcion() {
        return opcion;
    }
    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }
    public ArrayList<MenuSubOpciones> getSub_opciones() {
        return sub_opciones;
    }
    public void setSub_opciones(ArrayList<MenuSubOpciones> sub_opciones) {
        this.sub_opciones = sub_opciones;
    }

}


