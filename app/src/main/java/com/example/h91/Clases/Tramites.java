package com.example.h91.Clases;

import java.io.Serializable;
import java.util.Date;

public class Tramites implements Serializable { //TABLA TRAMITES QUE SON LOS DOCUMENTOS DE LOS EMPLEADOS
    private int id;
    private int idSolicitante;
    private String nombre_documento;
    private String asunto;
    private String comentario;
    private Date fecha_solicitud;
    private int idEstado;
    //
    public Tramites(int id, int idSolicitante, String nombre_documento, String asunto, String comentario, Date fecha_solicitud, int idEstado) {
        this.id = id;
        this.idSolicitante = idSolicitante;
        this.nombre_documento = nombre_documento;
        this.asunto = asunto;
        this.comentario = comentario;
        this.fecha_solicitud = fecha_solicitud;
        this.idEstado = idEstado;
    }
    public Tramites(int idSolicitante, String nombre_documento, String asunto, String comentario, Date fecha_solicitud, int idEstado) {
        this.idSolicitante = idSolicitante;
        this.nombre_documento = nombre_documento;
        this.asunto = asunto;
        this.comentario = comentario;
        this.fecha_solicitud = fecha_solicitud;
        this.idEstado=idEstado;
    }
    public Tramites(int idSolicitante, String nombre_documento, String asunto, Date fecha_solicitud, int idEstado) {
        this.idSolicitante = idSolicitante;
        this.nombre_documento = nombre_documento;
        this.asunto = asunto;
        this.fecha_solicitud = fecha_solicitud;
        this.idEstado=idEstado;
    }
    public Tramites(int id, int idSolicitante, String nombre_documento, String asunto, String comentario, Date fecha_solicitud) {
        this.id = id;
        this.idSolicitante = idSolicitante;
        this.nombre_documento = nombre_documento;
        this.asunto = asunto;
        this.comentario = comentario;
        this.fecha_solicitud = fecha_solicitud;
        this.idEstado=0;
    }
    public Tramites() {
        this.id = 0;
        this.idSolicitante = 0;
        this.nombre_documento = "";
        this.asunto = "";
        this.comentario = "";
        this.fecha_solicitud = null;
        this.idEstado = 0;
    }
    public Tramites(String asunto, String comentario) {
        this.asunto = asunto;
        this.comentario = comentario;
    }
    public Tramites(String nombre_documento, String asunto, int idEstado) {
        this.nombre_documento=nombre_documento;
        this.asunto = asunto;
        this.idEstado=idEstado;
    }
    //
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(int idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    public String getNombre_documento() {
        return nombre_documento;
    }

    public void setNombre_documento(String nombre_documento) {
        this.nombre_documento = nombre_documento;
    }
    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha_solicitud() {
        return fecha_solicitud;
    }

    public void setFecha_solicitud(Date fecha_solicitud) {
        this.fecha_solicitud = fecha_solicitud;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }
    //

    @Override
    public String toString() {
        return "Tramites{" +
                "id=" + id +
                ", idSolicitante=" + idSolicitante +
                ", nombre_documento='" + nombre_documento + '\'' +
                ", asunto='" + asunto + '\'' +
                ", comentario='" + comentario + '\'' +
                ", fecha_solicitud=" + fecha_solicitud +
                ", idEstado=" + idEstado +
                '}';
    }
}
