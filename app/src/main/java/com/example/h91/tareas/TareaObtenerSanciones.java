package com.example.h91.tareas;

import com.example.h91.Clases.Sanciones;
import com.example.h91.modelos.SancionesDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;


public class TareaObtenerSanciones implements Callable<ArrayList<Sanciones>> {
        @Override
        public ArrayList<Sanciones> call() throws Exception {
            ArrayList<Sanciones> sanciones = SancionesDB.obtenerSanciones();
            return sanciones;
        }
}
