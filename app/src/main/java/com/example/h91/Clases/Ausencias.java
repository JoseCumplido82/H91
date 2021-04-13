package com.example.h91.Clases;

import java.util.Date;

public class Ausencias {
    private int id;
    private int idSolicitante;
    private Date fecha;
    private int horas;
    private Date fecha_solicitud;
    private int idEstado;
    //constructores

    public Ausencias(int id, int idSolicitante, Date fecha, int horas, Date fecha_solicitud, int idEstado) {
        this.id = id;
        this.idSolicitante = idSolicitante;
        this.fecha = fecha;
        this.horas = horas;
        this.fecha_solicitud = fecha_solicitud;
        this.idEstado = idEstado;
    }
    public Ausencias() {
        this.id = 0;
        this.idSolicitante = 0;
        this.fecha = null;
        this.horas = 0;
        this.fecha_solicitud = null;
        this.idEstado = 0;
    }
    public Ausencias(int idSolicitante, Date fecha, int horas, Date fecha_solicitud, int idEstado) {
        this.idSolicitante = idSolicitante;
        this.fecha = null;
        this.horas = horas;
        this.fecha_solicitud = null;
        this.idEstado = 0;
    }
    //getter y setter


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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
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
    //to string

    @Override
    public String toString() {
        return "Ausencias{" +
                "id=" + id +
                ", idSolicitante=" + idSolicitante +
                ", fecha=" + fecha +
                ", horas=" + horas +
                ", fecha_solicitud=" + fecha_solicitud +
                ", idEstado=" + idEstado +
                '}';
    }
}
