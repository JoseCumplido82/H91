package com.example.h91.tareas;

import com.example.h91.Clases.Tramites;
import com.example.h91.modelos.TramitesDB;

import java.util.concurrent.Callable;

public class TareaActualizarTramites implements Callable<Boolean> {
        private Tramites t;
        public TareaActualizarTramites(Tramites t){
            this.t=t;
        }
        @Override
        public Boolean call() throws Exception {
            boolean actualizarOK= TramitesDB.actualizarTramitesTabla(this.t);
            return actualizarOK;
        }
}
