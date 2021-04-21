package com.example.h91.tareas;

import com.example.h91.Clases.Tramites;
import com.example.h91.modelos.TramitesDB;

import java.util.concurrent.Callable;


public class TareaInsertarTramites implements Callable<Boolean> {
        private Tramites t = null;

        public TareaInsertarTramites(Tramites t) {
            this.t = t;
        }

        @Override
        public Boolean call() throws Exception {
            boolean insertadoOK = TramitesDB.insertarTramiteTabla(t);
            return insertadoOK;
        }
}
