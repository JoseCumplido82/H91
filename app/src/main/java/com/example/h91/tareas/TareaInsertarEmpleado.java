package com.example.h91.tareas;

import com.example.h91.Clases.Empleado;
import com.example.h91.modelos.EmpleadoDB;

import java.util.concurrent.Callable;

public class TareaInsertarEmpleado implements Callable <Boolean> {

    private Empleado e = null;

    public TareaInsertarEmpleado(Empleado e){
        this.e=e;
    }

    @Override
    public Boolean call() throws Exception {
        boolean insertadoOK = EmpleadoDB.insertarEmpleadoTabla(e);
        return insertadoOK;
    }
}
