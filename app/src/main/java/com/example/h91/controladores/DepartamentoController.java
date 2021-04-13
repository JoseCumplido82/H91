package com.example.h91.controladores;

import com.example.h91.Clases.Departamento;
import com.example.h91.tareas.TareaActualizarDepartamento;
import com.example.h91.tareas.TareaBorrarDepartamento;
import com.example.h91.tareas.TareaInsertarDepartamento;
import com.example.h91.tareas.TareaObtenerDepartamentos;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class DepartamentoController {
    public static boolean insertarDepartamento(Departamento d){
        FutureTask t = new FutureTask(new TareaInsertarDepartamento(d));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean insercionOK = false;
        try {
            insercionOK = (boolean) t.get();
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

    public static ArrayList<Departamento> obtenerDepartamentos(){
        ArrayList<Departamento> departamentosDevueltos= null;
        FutureTask t = new FutureTask(new TareaObtenerDepartamentos());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        try {
            departamentosDevueltos = (ArrayList<Departamento>)t.get();
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
        return departamentosDevueltos;
    }

    public static boolean borrarDepartamento(Departamento d){
        FutureTask t = new FutureTask(new TareaBorrarDepartamento(d));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean borradoOK=false;
        try {
            borradoOK = (boolean) t.get();
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

    public static boolean actualizarDepartamento(Departamento d){
        FutureTask t = new FutureTask(new TareaActualizarDepartamento(d));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean actualizadoOK= false;
        try {
            actualizadoOK = (boolean)t.get();
            es.shutdown();
            try {
                if(!es.awaitTermination(800,TimeUnit.MILLISECONDS)){
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
            return actualizadoOK;
        }
    }

}
