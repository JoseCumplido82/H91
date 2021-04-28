package com.example.h91.tareas;

import com.example.h91.Clases.Tramites;
import com.example.h91.modelos.AusenciasDB;
import com.example.h91.modelos.TramitesDB;

import java.util.concurrent.Callable;
public class TareaObtenerAusenciaConIDUserLogin implements Callable<Boolean> {
        private int idEmpleado;
        public TareaObtenerAusenciaConIDUserLogin (int idEmpleado){
            this.idEmpleado=idEmpleado;
        }
        @Override
        public Boolean call() throws Exception{
            boolean comprobarOK= TramitesDB.IDEmpleadoAusencia(this.idEmpleado);
            return  comprobarOK;
        }

}
