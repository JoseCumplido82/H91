package com.example.h91.Clases;

import java.io.Serializable;
import java.util.Date;

public class Documentos implements Serializable { //TABLA DOCUMENTOS GENERALES
    //Atributos
   private int id;
   private int idEmpleado;
   private String nombre;
   private int es_plantilla;

   //constructores
    public Documentos(int id, int idEmpleado, String nombre, int es_plantilla) {
        this.id = id;
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.es_plantilla = es_plantilla;
    }

    public Documentos(int idEmpleado, String nombre, int es_plantilla) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.es_plantilla = es_plantilla;
    }

    public Documentos() {
        this.id = 0;
        this.idEmpleado = 0;
        this.nombre = "";
        this.es_plantilla = 0;
    }

    //getter y setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public int getEs_plantilla() {
        return es_plantilla;
    }

    public void setEs_plantilla(int es_plantilla) {
        this.es_plantilla = es_plantilla;
    }

    //to string
    @Override
    public String toString() {
        return "Documentos{" +
                "id=" + id +
                ", idEmpleado=" + idEmpleado +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
