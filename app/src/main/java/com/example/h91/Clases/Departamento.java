package com.example.h91.Clases;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.Objects;

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
    public Departamento(String nombre){
        this.nombre=nombre;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departamento)) return false;
        Departamento that = (Departamento) o;
        return id == that.id;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    //to string


    @Override
    public String toString() {
        return "Dpto. " + nombre + " " +id;
    }
}
