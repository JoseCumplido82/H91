package com.example.h91.Clases;

import java.io.Serializable;

public class Departamento implements Serializable {
    //atributos
    private int id;
    private int idResponsable;
    private String nombre;

    //constructores
    public Departamento(int id, int idResponsable, String nombre) {
        this.id = id;
        this.idResponsable = idResponsable;
        this.nombre = nombre;
    }
    public Departamento(int id, String nombre) {
        this.id = id;
        this.idResponsable=0;
        this.nombre = nombre;
    }
    public Departamento(int id){
        this.id=id;
    }
    public Departamento() {
        this.id = 0;
        this.idResponsable = 0;
        this.nombre = "";
    }
    //getter y setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(int idResponsable) {
        this.idResponsable = idResponsable;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

//


    //to string


    @Override
    public String toString() {
        return "Dpto. " + nombre + " " +id;
    }
}
