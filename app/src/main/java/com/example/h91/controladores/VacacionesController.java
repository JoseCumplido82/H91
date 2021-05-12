package com.example.h91.controladores;

import com.example.h91.Clases.Vacaciones;
import com.example.h91.tareas.TareaBorrarVacaciones;
import com.example.h91.tareas.TareaInsertarVacaciones;
import com.example.h91.tareas.TareaObtenerVacaciones;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class VacacionesController {
    public static boolean insertarVacaciones(Vacaciones v) {
        FutureTask t = new FutureTask(new TareaInsertarVacaciones(v));
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
        } finally {
            return insercionOK;
        }
    }
//-------------------------------------------------------------------------------
    public static ArrayList<Vacaciones> obtenerVacaciones(){
        ArrayList<Vacaciones> vacacionesDevueltas=null;
        FutureTask t= new FutureTask(new TareaObtenerVacaciones());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        try {
            vacacionesDevueltas= (ArrayList<Vacaciones>)t.get();
            es.shutdown();
            try {
                if(!es.awaitTermination(2000, TimeUnit.MILLISECONDS)){
                    es.shutdownNow();
                }
            }catch (InterruptedException e){
                es.shutdownNow();
            }
        } catch (ExecutionException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return vacacionesDevueltas;
    }
//-------------------------------------------------------------------------
    public static boolean borrarVacaciones(Vacaciones vacaciones)
    {
        FutureTask t = new FutureTask(new TareaBorrarVacaciones(vacaciones));
        ExecutorService es= Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean borradoOK= false;
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
}
