package com.example.h91.tareas;

import com.example.h91.Clases.Nominas;
import com.example.h91.modelos.NominasDB;

import java.util.concurrent.Callable;

public class TareaBorrarNominas implements Callable<Boolean> {
    private Nominas n= null;

    public TareaBorrarNominas (Nominas n){
        this.n=n;
    }
    @Override
    public Boolean call() throws Exception {
        boolean borradoOK= NominasDB.borrarNominaTabla(n);
        return borradoOK;
    }
}
