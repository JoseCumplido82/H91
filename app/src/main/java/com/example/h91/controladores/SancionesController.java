package com.example.h91.controladores;

import com.example.h91.Clases.Sanciones;
import com.example.h91.tareas.TareaActualizarSanciones;
import com.example.h91.tareas.TareaBorrarSanciones;
import com.example.h91.tareas.TareaInsertarSanciones;
import com.example.h91.tareas.TareaObtenerSanciones;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class SancionesController {
    public static boolean insertarSancion(Sanciones sanciones){
        FutureTask t= new FutureTask(new TareaInsertarSanciones(sanciones));
        ExecutorService es= Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean insercionOK=false;
        try {
            insercionOK=(boolean) t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)){
                    es.shutdownNow();
                }
            }catch (InterruptedException e){
                es.shutdownNow();
            }
        }catch (ExecutionException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
            return insercionOK;
        }
    }

    public static ArrayList<Sanciones> obtenerSanciones(){
        ArrayList<Sanciones> sancionesDevueltas= null;
        FutureTask t= new FutureTask(new TareaObtenerSanciones());
        ExecutorService es= Executors.newSingleThreadExecutor();
        es.submit(t);
        try {
            sancionesDevueltas= (ArrayList<Sanciones>)t.get();
            es.shutdown();
            try {
                if(!es.awaitTermination(2000, TimeUnit.MILLISECONDS)){
                    es.shutdownNow();
                }
            }catch (InterruptedException e){
                es.shutdownNow();
            }
        }catch (ExecutionException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return  sancionesDevueltas;
    }

    public static  boolean borrarSanciones(Sanciones sanciones)
    {
        FutureTask t= new FutureTask(new TareaBorrarSanciones(sanciones));
        ExecutorService es= Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean borradoOK=false;
        try {
            borradoOK=(boolean) t.get();
            es.shutdown();
            try {
                if(!es.awaitTermination(800, TimeUnit.MILLISECONDS)){
                    es.shutdownNow();
                }
            }catch (InterruptedException e){
                es.shutdownNow();
            }
        }catch (ExecutionException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
            return borradoOK;
        }
    }

    public static boolean actualizarSanciones(Sanciones sanciones)
    {
        FutureTask t = new FutureTask(new TareaActualizarSanciones(sanciones));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean actualizadoOK= false;
        try {
            actualizadoOK=(boolean) t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)){
                    es.shutdownNow();
                }
            }catch (InterruptedException e){
                es.shutdownNow();
            }
        }catch (ExecutionException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
            return  actualizadoOK;
        }
    }
}
