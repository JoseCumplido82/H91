package com.example.h91.Clases;

public class Estados {
    private int id;
    private String nombre;

    public Estados(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Estados(String nombre) {
        this.id=0;
        this.nombre = nombre;
    }
    public Estados() {
        this.id = 0;
        this.nombre = "";
    }
    public Estados(int id) {
        this.id = id;
        this.nombre = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public String toString() {
        return "Estados{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
