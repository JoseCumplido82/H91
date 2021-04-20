package com.example.h91.tareas;

import com.example.h91.Clases.Documentos;
import com.example.h91.modelos.DocumentosDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;


public class TareaObtenerDocumentos implements Callable<ArrayList<Documentos>> {


        @Override
        public ArrayList<Documentos> call() throws Exception {
            ArrayList<Documentos> documen= DocumentosDB.obtenerDocumentos();
            return documen;
        }
}
