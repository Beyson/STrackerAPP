package com.example.ssis_tracker.model;

public class Comentario {

    private int id;
    private String comentario;
    private int id_usuario;
    private String usuario;
    private String FechaComentario;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public int getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getFechaComentario() {
        return FechaComentario;
    }
    public void setFechaComentario(String fechaComentario) {
        FechaComentario = fechaComentario;
    }


}
