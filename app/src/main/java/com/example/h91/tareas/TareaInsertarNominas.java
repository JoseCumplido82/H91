package com.example.h91.tareas;

import com.example.h91.Clases.Nominas;
import com.example.h91.modelos.NominasDB;

import java.util.concurrent.Callable;

public class TareaInsertarNominas implements Callable<Boolean> {
    private Nominas n= null;

    public TareaInsertarNominas(Nominas n){
        this.n=n;
    }

    @Override
    public Boolean call() throws Exception {
        boolean insertadoOK= NominasDB.insertarNominaTabla(n);
        return insertadoOK;
    }
}
