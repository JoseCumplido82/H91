package com.example.h91.tareas;

import com.example.h91.Clases.Sanciones;
import com.example.h91.modelos.SancionesDB;

import java.util.concurrent.Callable;

public class TareaActualizarSanciones implements Callable<Boolean> {
        private Sanciones s;
        public TareaActualizarSanciones(Sanciones s){
            this.s=s;
        }
        @Override
        public Boolean call() throws Exception {
            boolean actualizarOK= SancionesDB.actualizarSancionesTabla(this.s);
            return actualizarOK;
        }
}
