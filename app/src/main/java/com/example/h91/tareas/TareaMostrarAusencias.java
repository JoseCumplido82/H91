package com.example.h91.tareas;

import com.example.h91.Clases.Ausencias;
import com.example.h91.Clases.Empleado;
import com.example.h91.modelos.AusenciasDB;


import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaMostrarAusencias implements Callable<ArrayList<Ausencias>> {
        private ArrayList<Ausencias> ausencias = null;
        @Override
        public ArrayList<Ausencias> call() throws Exception {
            ausencias = AusenciasDB.obtenerAusencia();
            return ausencias;
        }
}
