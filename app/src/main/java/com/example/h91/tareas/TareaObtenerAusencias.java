package com.example.h91.tareas;

import com.example.h91.Clases.Ausencias;
import com.example.h91.Clases.Empleado;
import com.example.h91.Clases.Tramites;
import com.example.h91.modelos.AusenciasDB;
import com.example.h91.modelos.EmpleadoDB;
import com.example.h91.modelos.TramitesDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaObtenerAusencias implements Callable<Boolean> {
        private int idEmpleado;
        public TareaObtenerAusencias (int idEmpleado){
        this.idEmpleado=idEmpleado;
        }
        @Override
    public Boolean call() throws Exception{
            boolean comprobarOK= TramitesDB.IDEmpleadoAusencia(this.idEmpleado);
            return  comprobarOK;
        }

}

