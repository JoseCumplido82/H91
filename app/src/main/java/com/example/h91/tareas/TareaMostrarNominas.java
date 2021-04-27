package com.example.h91.tareas;

import com.example.h91.Clases.Nominas;
import com.example.h91.modelos.NominasDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaMostrarNominas implements Callable<ArrayList<Nominas>> {
    private ArrayList<Nominas> nominas=null;
    @Override
    public ArrayList<Nominas> call() throws Exception {
        nominas= NominasDB.obtenerNominas();
        return nominas;
    }
}
