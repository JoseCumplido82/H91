package com.example.h91.controladores;

import com.example.h91.Clases.Documentos;
import com.example.h91.tareas.TareaActualizarDocumentos;
import com.example.h91.tareas.TareaBorrarDocumentos;
import com.example.h91.tareas.TareaInsertarDocumentos;
import com.example.h91.tareas.TareaObtenerDocumentos;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class DocumentosController {
    public static boolean insertarDocumentos(Documentos d){
        FutureTask t = new FutureTask(new TareaInsertarDocumentos(d));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean insercionOK = false;
        try {
            insercionOK = (boolean) t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (
                ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            return insercionOK;
        }
    }

    public static ArrayList<Documentos> obtenerDocumentos() {
        ArrayList<Documentos> documentosDevueltos = null;
        FutureTask t = new FutureTask (new TareaObtenerDocumentos());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        try {
            documentosDevueltos= (ArrayList<Documentos>)t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(2000, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return documentosDevueltos;
    }

    public static boolean borrarDocumentos(Documentos d)
    {
        FutureTask t = new FutureTask(new TareaBorrarDocumentos(d));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean borradoOK = false;
        try {
            borradoOK = (boolean) t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (
                ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            return borradoOK;
        }
    }

    public static boolean actualizarDocumentos(Documentos d) {
        FutureTask t = new FutureTask(new TareaActualizarDocumentos(d));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean actualizadoOK = false;
        try {
            actualizadoOK = (boolean) t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (
                ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            return actualizadoOK;
        }
    }
}
