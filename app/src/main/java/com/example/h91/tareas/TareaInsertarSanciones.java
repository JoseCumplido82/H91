package com.example.h91.tareas;

import com.example.h91.Clases.Sanciones;
import com.example.h91.modelos.SancionesDB;

import java.util.concurrent.Callable;


public class TareaInsertarSanciones implements Callable<Boolean> {
        private Sanciones s = null;

        public TareaInsertarSanciones(Sanciones s) {
            this.s = s;
        }

        @Override
        public Boolean call() throws Exception {
            boolean insertadoOK = SancionesDB.insertarSancionesTabla(s);
            return insertadoOK;
        }
}
