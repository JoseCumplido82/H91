package com.example.h91.tareas;

import com.example.h91.Clases.Tramites;
import com.example.h91.modelos.TramitesDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;


public class TareaObtenerTramites implements Callable<ArrayList<Tramites>> {
    private int idEmpleado;
    public TareaObtenerTramites(int idEmpleado){this.idEmpleado=idEmpleado;}
        @Override
        public ArrayList<Tramites> call() throws Exception {
            ArrayList<Tramites> tramites = TramitesDB.obtenerTramites(this.idEmpleado);
            return tramites;
        }

}
