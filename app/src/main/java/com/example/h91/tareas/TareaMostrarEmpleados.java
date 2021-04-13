package com.example.h91.tareas;

import com.example.h91.Clases.Empleado;
import com.example.h91.modelos.EmpleadoDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaMostrarEmpleados implements Callable<ArrayList<Empleado>> {
    private ArrayList<Empleado> empleados = null;
    @Override
    public ArrayList<Empleado> call() throws Exception {
        empleados = EmpleadoDB.obtenerEmpleados();
        return empleados;
    }
}
