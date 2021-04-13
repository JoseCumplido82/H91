package com.example.h91.tareas;

import com.example.h91.Clases.Departamento;
import com.example.h91.modelos.DepartamentoDB;

import java.util.concurrent.Callable;

public class TareaBorrarDepartamento implements Callable<Boolean> {
    private Departamento d = null;

    public TareaBorrarDepartamento(Departamento d){
        this.d=d;
    }

    @Override
    public Boolean call() throws Exception {
        boolean borradoOK = DepartamentoDB.borrarDepartamentoTabla(d);
        return borradoOK;
    }
}
