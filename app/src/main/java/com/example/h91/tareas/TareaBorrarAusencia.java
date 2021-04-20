package com.example.h91.tareas;

import com.example.h91.Clases.Ausencias;
import com.example.h91.Clases.Empleado;
import com.example.h91.modelos.AusenciasDB;
import com.example.h91.modelos.EmpleadoDB;

import java.util.concurrent.Callable;

public class TareaBorrarAusencia implements Callable<Boolean> {
        private Ausencias a = null;
        public TareaBorrarAusencia(Ausencias a){
            this.a=a;
        }

        @Override
        public Boolean call() throws Exception {
            boolean borradoOK = AusenciasDB.borrarAusenciaTabla(a);
            return borradoOK;
        }
}
