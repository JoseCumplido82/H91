package com.example.h91.tareas;

import com.example.h91.Clases.Empleado;
import com.example.h91.modelos.EmpleadoDB;

import java.util.concurrent.Callable;

public class TareaBorrarEmpleado implements Callable<Boolean> {
    private Empleado e = null;
    public TareaBorrarEmpleado(Empleado e){
        this.e=e;
    }

    @Override
    public Boolean call() throws Exception {
        boolean borradoOK = EmpleadoDB.borrarEmpleadoTabla(e);
        return borradoOK;
    }
}
