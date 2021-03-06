package com.example.h91.controladores;

import com.example.h91.Clases.Tramites;
import com.example.h91.tareas.TareaActualizarTramites;
import com.example.h91.tareas.TareaBorrarTramites;
import com.example.h91.tareas.TareaInsertarTramites;
import com.example.h91.tareas.TareaObtenerIDEmpleado;
import com.example.h91.tareas.TareaObtenerTramites;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class TramitesController {

    public static boolean insertarTramites(Tramites tramites){
        FutureTask t = new FutureTask(new TareaInsertarTramites(tramites));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean insercionOK= false;
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
//---------------------------------------------------------------------------
    public static ArrayList<Tramites> obtenerTramites(int idempleado){
        ArrayList<Tramites> tramitesDevueltos=null;
        FutureTask t= new FutureTask(new TareaObtenerTramites(idempleado));
        ExecutorService es= Executors.newSingleThreadExecutor();
        es.submit(t);
        try {
            tramitesDevueltos=(ArrayList<Tramites>)t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(2000, TimeUnit.MILLISECONDS)){
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
        return tramitesDevueltos;
    }
//--------------------------------------------------------------------------
    public static boolean obtenerIDempleadoTramite(int dni){
        FutureTask t = new FutureTask(new TareaObtenerIDEmpleado(dni));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean obtenidoOK= false;
        try{
            obtenidoOK = (boolean)t.get();
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
            return obtenidoOK;
        }
    }
    //------------------------------------------------------------------------------
    public static boolean borrarTramites(Tramites t)
    {
        FutureTask task= new FutureTask(new TareaBorrarTramites(t));
        ExecutorService es=  Executors.newSingleThreadExecutor();
        es.submit(task);
        boolean borradoOK=false;
        try {
            borradoOK=(boolean)task.get();
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
            return borradoOK;
        }
    }
    //----------------------------------------------------------------------------
    public static boolean actualizarTramites(Tramites tramites){
        FutureTask t= new FutureTask(new TareaActualizarTramites(tramites));
        ExecutorService es= Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean actualizadoOK= false;
        try {
            actualizadoOK= (boolean) t.get();
            es.shutdown();
            try {
                if(!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
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
