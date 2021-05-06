package com.example.h91.Clases;

import java.io.Serializable;

public class Sanciones implements Serializable {
    private int id;
    private int idSancionado;
    private String motivo;
    private String observacion;
    //----------------------------------
    public Sanciones(int id, int idSancionado, String motivo, String observacion) {
        this.id = id;
        this.idSancionado = idSancionado;
        this.motivo = motivo;
        this.observacion = observacion;
    }
    public Sanciones(int idSancionado, String motivo, String observacion) {
        this.idSancionado = idSancionado;
        this.motivo = motivo;
        this.observacion = observacion;
    }
    public Sanciones() {
        this.id = 0;
        this.idSancionado = 0;
        this.motivo = "";
        this.observacion = "";
    }
    //----------------------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSancionado() {
        return idSancionado;
    }

    public void setIdSancionado(int idSancionado) {
        this.idSancionado = idSancionado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    //--------------------------------------------
    @Override
    public String toString() {
        return "Sanciones{" +
                "idSancionado=" + idSancionado +
                ", motivo='" + motivo + '\'' +
                '}';
    }
}
