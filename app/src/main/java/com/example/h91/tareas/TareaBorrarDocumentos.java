package com.example.h91.tareas;

import com.example.h91.Clases.Documentos;
import com.example.h91.modelos.DocumentosDB;

import java.util.concurrent.Callable;

public class TareaBorrarDocumentos implements Callable<Boolean> {
        private Documentos d = null;

        public TareaBorrarDocumentos(Documentos d) {
            this.d = d;
        }

        @Override
        public Boolean call() throws Exception {
            boolean borradoOK = DocumentosDB.borrarDocumentosTabla(d);
            return borradoOK;
        }
}
