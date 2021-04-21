package com.example.h91.Clases;

import java.io.Serializable;
import java.util.Date;

public class Ausencias implements Serializable {
    private int id;
    private int idSolicitante;
    private Date fecha_inicio;
    private int hora_inicio;
    private int horas;
    private Date fecha_solicitud;
    private String motivo;
    private int idEstado;
    //constructores


    public Ausencias(int id, int idSolicitante, Date fecha_inicio, int hora_inicio, int horas, Date fecha_solicitud, String motivo, int idEstado) {
        this.id = id;
        this.idSolicitante = idSolicitante;
        this.fecha_inicio = fecha_inicio;
        this.hora_inicio = hora_inicio;
        this.horas = horas;
        this.fecha_solicitud = fecha_solicitud;
        this.motivo = motivo;
        this.idEstado = idEstado;
    }

    public Ausencias() {
        this.id = 0;
        this.idSolicitante = 0;
        this.fecha_inicio = null;
        this.hora_inicio = 0;
        this.horas = 0;
        this.fecha_solicitud = null;
        this.motivo = "";
        this.idEstado = 1;
    }
    public Ausencias(int idSolicitante, Date fecha_inicio,int hora_inicio, int horas, Date fecha_solicitud, String motivo, int idEstado) {
        this.idSolicitante = idSolicitante;
        this.fecha_inicio = fecha_inicio;
        this.hora_inicio=hora_inicio;
        this.horas = horas;
        this.fecha_solicitud = fecha_solicitud;
        this.motivo=motivo;
        this.idEstado = 1;
    }
    //getter y setter


    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public int getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(int hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

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
        return fecha_inicio;
    }

    public void setFecha(Date fecha) {
        this.fecha_inicio = fecha_inicio;
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
                ", fecha_inicio=" + fecha_inicio +
                ", horas=" + horas +
                ", fecha_solicitud=" + fecha_solicitud +
                ", idEstado=" + idEstado +
                '}';
    }
}
