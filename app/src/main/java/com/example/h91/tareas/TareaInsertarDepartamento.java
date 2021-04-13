package com.example.h91.tareas;

import android.telecom.Call;

import com.example.h91.Clases.Departamento;
import com.example.h91.modelos.DepartamentoDB;

import java.util.concurrent.Callable;

public class TareaInsertarDepartamento implements Callable<Boolean> {
    private Departamento d = null;

    public TareaInsertarDepartamento(Departamento d){
        this.d=d;
    }

    @Override
    public Boolean call() throws Exception {
        boolean insertadoOK = DepartamentoDB.insertarDepartamentoTabla(d);
        return insertadoOK;
    }
}
