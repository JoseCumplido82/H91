package com.example.h91.Clases;

import java.io.Serializable;
import java.util.Date;

public class Vacaciones implements Serializable {
    private int id;
    private int idSolicitante;
    private Date fecha_inicio;
    private Date fecha_fin;
    private int dias;
    private Date fecha_solicitud;
    private int idEstado;

    //-----------


    public Vacaciones(int id, int idSolicitante, Date fecha_inicio, Date fecha_fin,int dias, Date fecha_solicitud, int idEstado) {
        this.id = id;
        this.idSolicitante = idSolicitante;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.fecha_solicitud = fecha_solicitud;
        this.dias=dias;
        this.idEstado = idEstado;
    }
    public Vacaciones() {
        this.id = 0;
        this.idSolicitante = 0;
        this.fecha_inicio = null;
        this.fecha_fin = null;
        this.fecha_solicitud = null;
        this.dias=0;
        this.idEstado = 0;
    }

    public Vacaciones(int idSolicitante, Date fecha_inicio, Date fecha_fin, int dias, Date fecha_solicitud, int idEstado) {
        this.idSolicitante = idSolicitante;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.dias = dias;
        this.fecha_solicitud = fecha_solicitud;
        this.idEstado = 1;
    }

    public Vacaciones(int id, int idSolicitante, Date fecha_inicio, Date fecha_fin, Date fecha_solicitud) {
        this.id = id;
        this.idSolicitante = idSolicitante;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.fecha_solicitud = fecha_solicitud;
    }
    //-----------------


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

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
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

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }
    //

    @Override
    public String toString() {
        return "Vacaciones{" +
                "id=" + id +
                ", idSolicitante=" + idSolicitante +
                ", fecha_inicio=" + fecha_inicio +
                ", fecha_fin=" + fecha_fin +
                ", fecha_solicitud=" + fecha_solicitud +
                ", idEstado=" + idEstado +
                '}';
    }
}
