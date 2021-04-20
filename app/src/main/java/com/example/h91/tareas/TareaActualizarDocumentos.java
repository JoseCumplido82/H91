package com.example.h91.tareas;

import com.example.h91.Clases.Documentos;
import com.example.h91.modelos.DocumentosDB;

import java.util.concurrent.Callable;


public class TareaActualizarDocumentos implements Callable<Boolean> {
        private Documentos d;
        public TareaActualizarDocumentos(Documentos d){
            this.d=d;
        }
        @Override
        public Boolean call() throws Exception {
            boolean actualizarOK= DocumentosDB.actualizarDocumentosTabla(this.d);
            return actualizarOK;
        }
}
