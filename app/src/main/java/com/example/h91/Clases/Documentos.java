package com.example.h91.Clases;

import java.io.Serializable;
import java.util.Date;

public class Documentos implements Serializable {
    //Atributos
   private int id;
   private int idEmpleado;
   private String nombre;
   private Date fecha_subida;

   //constructores
    public Documentos(int id, int idEmpleado, String nombre, Date fecha_subida) {
        this.id = id;
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.fecha_subida = fecha_subida;
    }

    public Documentos(int idEmpleado, String nombre, Date fecha_subida) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.fecha_subida = fecha_subida;
    }

    public Documentos() {
        this.id = 0;
        this.idEmpleado = 0;
        this.nombre = "";
        this.fecha_subida = null;
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



    public Date getFecha_subida() {
        return fecha_subida;
    }

    public void setFecha_subida(Date fecha_subida) {
        this.fecha_subida = fecha_subida;
    }


    //to string
    @Override
    public String toString() {
        return "Documentos{" +
                "id=" + id +
                ", idEmpleado=" + idEmpleado +
                ", nombre='" + nombre + '\'' +
                ", fecha_subida=" + fecha_subida +
                '}';
    }
}
