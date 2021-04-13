package com.example.h91.Clases;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Solicitud implements Serializable {
    private String asunto;
    private String comentario;
    private Bitmap imagen;

    public Solicitud(String asunto, String comentario, Bitmap imagen) {
        this.asunto = asunto;
        this.comentario = comentario;
        this.imagen = imagen;
    }

    public Solicitud(String asunto, String comentario) {
        this.asunto = asunto;
        this.comentario = comentario;
        this.imagen=null;
    }
    public Solicitud() {
        this.asunto = "";
        this.comentario = "";
        this.imagen = null;
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

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Solicitud{" +
                "asunto='" + asunto + '\'' +
                ", comentario='" + comentario +
                '}';
    }
}
