package com.example.h91.controladores;

import android.widget.TextView;

import com.example.h91.Clases.Empleado;
import com.example.h91.tareas.TareaComprobarEmpleado;
import com.example.h91.tareas.TareaBorrarEmpleado;
import com.example.h91.tareas.TareaInsertarEmpleado;
import com.example.h91.tareas.TareaMostrarEmpleados;
import com.example.h91.tareas.TareaObtenerEmpleados;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class EmpleadoController {

    public static ArrayList<Empleado> obtenerEmpleados()
    {
        ArrayList<Empleado> empleadosDevueltos =null;
        FutureTask t = new FutureTask(new TareaObtenerEmpleados());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        try {
            empleadosDevueltos = (ArrayList<Empleado>)t.get();
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
        return empleadosDevueltos;
    }
    //---------------------------------------------------

    public static void MostrarEmpleados (TextView txt_empleados)
    {
        FutureTask t = new FutureTask(new TareaMostrarEmpleados());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        try {
            ArrayList<Empleado> empleadosDevueltos= (ArrayList<Empleado>)t.get();
            es.shutdown();
            try {
                if(!es.awaitTermination(800, TimeUnit.MILLISECONDS)){
                    es.shutdownNow();
                }
            }catch (InterruptedException e){
                es.shutdownNow();
            }
            String texto_empleados = "empleado \n";
            if(empleadosDevueltos !=null){
                for (Empleado e : empleadosDevueltos){
                    texto_empleados += e.toString() + "\n";
                }
                txt_empleados.setText(texto_empleados);
            }else{
                txt_empleados.setText("no se recuperaron los empleados");
            }
        }catch (ExecutionException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static boolean InsertarEmpleado(Empleado em){
        FutureTask t = new FutureTask(new TareaInsertarEmpleado(em));
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

    public static boolean borrarEmpleado(Empleado esleccionado){
        FutureTask t = new FutureTask(new TareaBorrarEmpleado(esleccionado));
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
    public static boolean comprobarEmpleado(Empleado empleado){
        FutureTask t = new FutureTask(new TareaComprobarEmpleado(empleado));
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
