package com.example.h91.tareas;

import com.example.h91.Clases.Empleado;
import com.example.h91.modelos.EmpleadoDB;

import java.util.concurrent.Callable;


public class TareaActualizarEmpleado implements Callable<Boolean> {
        private Empleado e;
        public TareaActualizarEmpleado(Empleado e){
            this.e=e;
        }
        @Override
        public Boolean call() throws Exception {
            boolean actualizarOK= EmpleadoDB.actualizarEmpleadoTabla(this.e);
            return actualizarOK;
        }
}
