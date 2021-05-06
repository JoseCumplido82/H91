package com.example.h91;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.h91.Clases.Empleado;
import com.example.h91.controladores.EmpleadoController;

import java.util.ArrayList;

public class ActivityBorrarEmpleado extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner sp_empleadoEliminar=null;
    Empleado eseleccionado =null;
    ArrayAdapter<Empleado> adapter=null;
    ArrayList<Empleado> empleados=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_empleado);
        sp_empleadoEliminar = (Spinner) findViewById(R.id.sp_empleadoEliminar);
        if(sp_empleadoEliminar!=null){
            sp_empleadoEliminar.setOnItemSelectedListener(this);
            empleados = EmpleadoController.obtenerEmpleados();
            if(empleados!=null){
                adapter= new ArrayAdapter<Empleado>(this, R.layout.item_empleado, empleados);
                sp_empleadoEliminar.setAdapter(adapter);
            }
        }

        //boton volver a panel RRHH
        Button bt_volver17 = (Button) findViewById(R.id.bt_volver17);
        bt_volver17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), ActivityGestionPlantilla.class);
                startActivityForResult(intent, 0);
            }
        });

    }

    public void mostrarToast(String mensaje)
    {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void borrarEmpleado(View view){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("¿Desea borrar el empleado?");
        alerta.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(eseleccionado == null)
                {
                    mostrarToast("selecciona un empleado");
                    return;
                }
                //borrar empleado
                boolean borradoOK= EmpleadoController.borrarEmpleado(eseleccionado);
                if(borradoOK)
                {
                    mostrarToast("empleado borrado correctamente");
                    System.out.println("empleado borrado correctamente");
                    adapter.clear();
                    empleados = EmpleadoController.obtenerEmpleados();
                    adapter.addAll(empleados);
                }
                else{
                    mostrarToast("el empleado no se pudo borrar");
                }
            }
        });
        alerta.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alerta.show();
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Empleado e = (Empleado) sp_empleadoEliminar.getItemAtPosition(position);
        eseleccionado = e;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




}