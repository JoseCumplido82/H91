package com.example.h91.tareas;

import com.example.h91.Clases.Departamento;
import com.example.h91.Clases.Empleado;
import com.example.h91.modelos.DepartamentoDB;
import com.example.h91.modelos.EmpleadoDB;

import java.util.concurrent.Callable;

public class TareaComprobarEmpleado implements Callable<Boolean> {
        private Empleado e;
        public TareaComprobarEmpleado(Empleado e){
            this.e=e;
        }

        @Override
        public Boolean call() throws Exception {
            boolean comprobarOK = EmpleadoDB.EmpleadoEnTabla(e.getUsuario(), e.getPass());
            return comprobarOK;
        }
}
