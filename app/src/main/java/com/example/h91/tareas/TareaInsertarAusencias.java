package com.example.h91.tareas;


import com.example.h91.Clases.Ausencias;
import com.example.h91.modelos.AusenciasDB;

import java.util.concurrent.Callable;


public class TareaInsertarAusencias implements Callable<Boolean> {

        private Ausencias a = null;

        public TareaInsertarAusencias(Ausencias a){
            this.a=a;
        }

        @Override
        public Boolean call() throws Exception {
            boolean insertadoOK = AusenciasDB.insertarAusenciaTabla(a);
            return insertadoOK;
        }
}
