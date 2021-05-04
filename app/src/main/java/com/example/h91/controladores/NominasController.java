package com.example.h91.controladores;

import android.widget.TextView;

import com.example.h91.Clases.Nominas;
import com.example.h91.tareas.TareaBorrarNominas;
import com.example.h91.tareas.TareaInsertarNominas;
import com.example.h91.tareas.TareaMostrarNominas;
import com.example.h91.tareas.TareaObtenerIDEmpleado;
import com.example.h91.tareas.TareaObtenerNominas;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class NominasController  {
    public static ArrayList<Nominas> obtenerNominas(int dni){
        ArrayList<Nominas> nominasDevueltas= null;
        FutureTask t= new FutureTask(new TareaObtenerNominas(dni));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        try {
            nominasDevueltas=(ArrayList<Nominas>)t.get();
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
        return nominasDevueltas;
    }

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


    public static void MostrarNominas(TextView txt_nominas)
    {
        FutureTask t= new FutureTask(new TareaMostrarNominas());
        ExecutorService es= Executors.newSingleThreadExecutor();
        es.submit(t);
        try {
            ArrayList<Nominas> nominasDevueltas= (ArrayList<Nominas>)t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)){
                    es.shutdownNow();
                }
            }catch (InterruptedException e){
                es.shutdownNow();
            }
            String texto_nominas= "NOMINAS \n";
            if(nominasDevueltas!=null){
                for (Nominas n: nominasDevueltas){
                    texto_nominas+=n.toString() + "\n";
                }
                txt_nominas.setText(texto_nominas);

            }else {
                txt_nominas.setText("no se recuperaron las nominas");
            }
        }catch (ExecutionException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    //---------------------------------
    public static boolean InsertarNomina(Nominas c)
    {
        FutureTask task= new FutureTask(new TareaInsertarNominas(c));
        ExecutorService es= Executors.newSingleThreadExecutor();
        es.submit(task);
        boolean insercionOK= false;
        try {
            insercionOK=(boolean) task.get();
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

    public static boolean borrarNominas(Nominas nseleccionada){
        FutureTask task= new FutureTask(new TareaBorrarNominas(nseleccionada));
        ExecutorService es= Executors.newSingleThreadExecutor();
        es.submit(task);
        boolean borradoOK=false;
        try {
            borradoOK=(boolean) task.get();
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
            return borradoOK;
        }
    }

}


