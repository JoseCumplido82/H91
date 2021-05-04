package com.example.h91.tareas;

import com.example.h91.Clases.Nominas;
import com.example.h91.modelos.NominasDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaObtenerNominas implements Callable<ArrayList<Nominas>> {
    private int idempleado;
    public TareaObtenerNominas(int idempleado){this.idempleado=idempleado;}
    @Override
    public ArrayList<Nominas> call() throws Exception {
        ArrayList<Nominas> nominas= NominasDB.obtenerNominas(idempleado);
        return nominas;
    }
}
