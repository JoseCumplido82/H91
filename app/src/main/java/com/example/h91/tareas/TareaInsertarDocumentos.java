package com.example.h91.tareas;

import com.example.h91.Clases.Documentos;
import com.example.h91.modelos.DocumentosDB;

import java.util.concurrent.Callable;

public class TareaInsertarDocumentos implements Callable<Boolean> {
        private Documentos d = null;

        public TareaInsertarDocumentos(Documentos d) {
            this.d = d;
        }

        @Override
        public Boolean call() throws Exception {
            boolean insertadoOK = DocumentosDB.insertarDocumentoTabla(d);
            return insertadoOK;
        }
}
