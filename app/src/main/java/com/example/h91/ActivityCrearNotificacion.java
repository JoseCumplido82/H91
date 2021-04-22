package com.example.h91;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.h91.Clases.Empleado;
import com.example.h91.Clases.listaEmpleadosAdapter;
import com.example.h91.controladores.EmpleadoController;

import java.util.ArrayList;

public class ActivityCrearNotificacion extends AppCompatActivity {

   private CheckBox cb_seleccionar;
   private CheckBox checkBox2;
   private RecyclerView rv_empleadosNotificaciones;
   private listaEmpleadosAdapter empleadosAdapter;
   private ArrayList<Empleado> empleados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_notificacion);
        empleados= EmpleadoController.obtenerEmpleados();
        cb_seleccionar=(CheckBox)findViewById(R.id.cb_seleccionar);
        checkBox2=(CheckBox)findViewById(R.id.checkBox2);

        if(empleados!=null){
            rv_empleadosNotificaciones=(RecyclerView)findViewById(R.id.rv_empleadosNotificaciones);
            empleadosAdapter = new listaEmpleadosAdapter(this, empleados);
            rv_empleadosNotificaciones.setAdapter(empleadosAdapter);
            rv_empleadosNotificaciones.setLayoutManager(new LinearLayoutManager(this));
        }else {
            Log.i("empleados", "no pude recuperar los empleados");
        }





        //boton volver a panel RRHH
        Button bt_volver19 = (Button) findViewById(R.id.bt_volver19);
        bt_volver19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), ActivityRRHH.class);
                startActivityForResult(intent, 0);
            }
        });


    }




    public void crearMensaje(View view){


       if( cb_seleccionar.isChecked()==true){
           mostrarToast("prueba " + cb_seleccionar.isChecked());
           Intent intent = new Intent(this, ActivityMensajeNotificaciones.class);
          // intent.putExtra("nombre",nombre );
           startActivity(intent);
       }
       else {
           mostrarToast("Debes seleccionar al menos un empleado ");
       }

    }


    public void mostrarToast(String mensaje)
    {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }
}