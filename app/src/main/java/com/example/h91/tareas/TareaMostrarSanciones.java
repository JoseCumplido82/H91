package com.example.h91.tareas;

import com.example.h91.Clases.Sanciones;
import com.example.h91.modelos.SancionesDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaMostrarSanciones  implements Callable<ArrayList<Sanciones>> {
        private ArrayList<Sanciones> sanciones = null;
        @Override
        public ArrayList<Sanciones> call() throws Exception {
            sanciones = SancionesDB.obtenerSanciones();
            return sanciones;
        }
}
