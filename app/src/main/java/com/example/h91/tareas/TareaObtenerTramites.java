package com.example.h91.tareas;

import com.example.h91.Clases.Tramites;
import com.example.h91.modelos.TramitesDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;


public class TareaObtenerTramites implements Callable<ArrayList<Tramites>> {
        @Override
        public ArrayList<Tramites> call() throws Exception {
            ArrayList<Tramites> medicamentos = TramitesDB.obtenerTramites();
            return medicamentos;
        }

}
