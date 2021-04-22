package com.example.h91.tareas;

import com.example.h91.Clases.Sanciones;
import com.example.h91.modelos.SancionesDB;

import java.util.concurrent.Callable;


public class TareaBorrarSanciones implements Callable<Boolean> {
        private Sanciones s = null;

        public TareaBorrarSanciones(Sanciones s) {
            this.s = s;
        }

        @Override
        public Boolean call() throws Exception {
            boolean borradoOK = SancionesDB.borrarSancionesTabla(s) ;
            return borradoOK;
        }
}
