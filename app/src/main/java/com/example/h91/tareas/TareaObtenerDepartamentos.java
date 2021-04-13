package com.example.h91.tareas;

import com.example.h91.Clases.Departamento;
import com.example.h91.modelos.DepartamentoDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaObtenerDepartamentos  implements Callable<ArrayList<Departamento>> {
    @Override
    public ArrayList<Departamento> call() throws Exception {
        ArrayList<Departamento> departamentos = DepartamentoDB.obtenerDepartamento();
        return departamentos;
    }
}
