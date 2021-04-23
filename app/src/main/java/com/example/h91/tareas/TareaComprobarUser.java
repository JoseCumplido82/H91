package com.example.h91.tareas;

import com.example.h91.Clases.Empleado;
import com.example.h91.modelos.EmpleadoDB;

import java.util.concurrent.Callable;

public class TareaComprobarUser implements Callable<Boolean> {
    private String user;
    private String pass;

    public TareaComprobarUser(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    @Override
    public Boolean call() throws Exception {
        boolean comprobarOK = EmpleadoDB.EmpleadoEnTabla2(this.user, this.pass);
        return comprobarOK;
    }
}