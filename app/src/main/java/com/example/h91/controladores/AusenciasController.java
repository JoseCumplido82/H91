package com.example.h91.controladores;

import android.widget.TextView;

import com.example.h91.Clases.Ausencias;
import com.example.h91.tareas.TareaBorrarAusencia;
import com.example.h91.tareas.TareaInsertarAusencias;
import com.example.h91.tareas.TareaMostrarAusencias;
import com.example.h91.tareas.TareaObtenerAusencias;
import com.example.h91.tareas.TareaObtenerIDEmpleado;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class AusenciasController {

    public static ArrayList<Ausencias> obtenerAusencias(int idUser)
    {
        ArrayList<Ausencias> ausenciasDevueltas =null;
        FutureTask t = new FutureTask(new TareaObtenerAusencias(idUser));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        try {
            ausenciasDevueltas = (ArrayList<Ausencias>)t.get();
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
        return ausenciasDevueltas;
    }
    //---------------------------------------------------

    public static void MostrarAusencias (TextView txt_ausencias)
    {
        FutureTask t = new FutureTask(new TareaMostrarAusencias());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        try {
            ArrayList<Ausencias> ausenciasDevueltas= (ArrayList<Ausencias>)t.get();
            es.shutdown();
            try {
                if(!es.awaitTermination(800, TimeUnit.MILLISECONDS)){
                    es.shutdownNow();
                }
            }catch (InterruptedException e){
                es.shutdownNow();
            }
            String texto_ausencias = "ausencias \n";
            if(ausenciasDevueltas !=null){
                for (Ausencias a : ausenciasDevueltas){
                    texto_ausencias += a.toString() + "\n";
                }
                txt_ausencias.setText(texto_ausencias);
            }else{
                txt_ausencias.setText("no se recuperaron las ausencias");
            }
        }catch (ExecutionException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static boolean InsertarAusencias(Ausencias au){
        FutureTask t = new FutureTask(new TareaInsertarAusencias(au));
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

    public static boolean obtenerIDempleadoAusencia(int dni){
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


    public static boolean borrarAusencia(Ausencias asleccionada){
        FutureTask t = new FutureTask(new TareaBorrarAusencia(asleccionada));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean borradoOK= false;
        try{
            borradoOK = (boolean)t.get();
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
