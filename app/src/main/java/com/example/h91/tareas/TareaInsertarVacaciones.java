package com.example.h91.tareas;


import com.example.h91.Clases.Vacaciones;
import com.example.h91.modelos.VacacionesDB;

import java.util.concurrent.Callable;

public class TareaInsertarVacaciones  implements Callable<Boolean> {
    private Vacaciones v=null;
    public TareaInsertarVacaciones(Vacaciones v){
        this.v=v;
    }
    @Override
    public Boolean call() throws Exception {
        boolean insertadoOK= VacacionesDB.insertarVacacionesTabla(v);
        return  insertadoOK;
    }
}
