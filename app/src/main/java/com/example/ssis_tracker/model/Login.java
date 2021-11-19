package com.example.ssis_tracker.model;

import java.util.ArrayList;

public class Login
{
    private int id_usuario;
    private String nombre;
    private String unidad;
    private int id_unidad;
    private String direccion;
    private int id_direccion;
    private String siglas;
    private int id_rol;
    private String rol;
    private boolean cambiar_password;
    private ArrayList<Menu> menu;

    public int getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getUnidad() {
        return unidad;
    }
    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getSiglas() {
        return siglas;
    }
    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }
    public int getId_rol() {
        return id_rol;
    }
    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    public boolean isCambiar_password() {
        return cambiar_password;
    }
    public void setCambiar_password(boolean cambiar_password) {
        this.cambiar_password = cambiar_password;
    }
    public ArrayList<Menu> getMenu() {
        return menu;
    }
    public void setMenu(ArrayList<Menu> menu) {
        this.menu = menu;
    }
    public int getId_unidad() {
        return id_unidad;
    }
    public void setId_unidad(int id_unidad) {
        this.id_unidad = id_unidad;
    }
    public int getId_direccion() {
        return id_direccion;
    }
    public void setId_direccion(int id_direccion) {
        this.id_direccion = id_direccion;
    }
}
