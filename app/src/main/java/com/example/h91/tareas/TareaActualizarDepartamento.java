package com.example.h91.tareas;

import com.example.h91.Clases.Departamento;
import com.example.h91.modelos.DepartamentoDB;

import java.util.concurrent.Callable;

public class TareaActualizarDepartamento implements Callable<Boolean> {
    private Departamento d;
    public TareaActualizarDepartamento(Departamento d){
        this.d=d;
    }

    @Override
    public Boolean call() throws Exception {
        boolean actualizarOK = DepartamentoDB.actualizarDepartamentoTabla(this.d);
        return actualizarOK;
    }
}
