package com.example.h91.tareas;

import com.example.h91.Clases.Empleado;
import com.example.h91.modelos.EmpleadoDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaObtenerEmpleados implements Callable <ArrayList<Empleado>> {

    @Override
    public ArrayList<Empleado> call() throws Exception {
        ArrayList<Empleado> empleados = EmpleadoDB.obtenerEmpleados();
        return empleados;
    }
}
