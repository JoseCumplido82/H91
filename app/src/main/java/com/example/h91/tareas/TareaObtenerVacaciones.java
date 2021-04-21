package com.example.h91.tareas;

import com.example.h91.Clases.Vacaciones;
import com.example.h91.modelos.VacacionesDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaObtenerVacaciones implements Callable<ArrayList<Vacaciones>> {
        @Override
        public ArrayList<Vacaciones> call() throws Exception {
            ArrayList<Vacaciones> vacaciones = VacacionesDB.obtenerVacaciones();
            return vacaciones;
        }
}
