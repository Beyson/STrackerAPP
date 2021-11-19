package com.example.ssis_tracker.model;

import java.util.ArrayList;

public class Noticias
{

    private int    notificacion_id;
    private String titulo_notificacion;
    private String mensaje_notificacion;
    private String usuaio_notificacion;
    private String fecha_notificacion;
    private ArrayList<Actividad.documentos_adjuntos> Imagenes;

    public int getNotificacion_id() {
        return notificacion_id;
    }
    public void setNotificacion_id(int notificacion_id) {
        this.notificacion_id = notificacion_id;
    }
    public String getTitulo_notificacion() {
        return titulo_notificacion;
    }
    public void setTitulo_notificacion(String titulo_notificacion) {
        this.titulo_notificacion = titulo_notificacion;
    }
    public String getMensaje_notificacion() {
        return mensaje_notificacion;
    }
    public void setMensaje_notificacion(String mensaje_notificacion) {
        this.mensaje_notificacion = mensaje_notificacion;
    }
    public String getUsuaio_notificacion() {
        return usuaio_notificacion;
    }
    public void setUsuaio_notificacion(String usuaio_notificacion) {
        this.usuaio_notificacion = usuaio_notificacion;
    }
    public String getFecha_notificacion() {
        return fecha_notificacion;
    }
    public void setFecha_notificacion(String fecha_notificacion) {
        this.fecha_notificacion = fecha_notificacion;
    }
    public ArrayList<Actividad.documentos_adjuntos> getImagenes() {
        return Imagenes;
    }
    public void setImagenes(ArrayList<Actividad.documentos_adjuntos> imagenes) {
        Imagenes = imagenes;
    }

}
