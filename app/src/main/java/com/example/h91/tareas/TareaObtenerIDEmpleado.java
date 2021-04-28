package com.example.h91.tareas;

import com.example.h91.Clases.Empleado;
import com.example.h91.modelos.AusenciasDB;
import com.example.h91.modelos.EmpleadoDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaObtenerIDEmpleado implements Callable<Boolean>{
    private int usuario;

    public TareaObtenerIDEmpleado(int usuario) {
        this.usuario = usuario;
    }
    @Override
    public Boolean call() throws Exception {
        boolean comprobarOK = EmpleadoDB.EmpleadoID(this.usuario);
        return comprobarOK;
    }
}
