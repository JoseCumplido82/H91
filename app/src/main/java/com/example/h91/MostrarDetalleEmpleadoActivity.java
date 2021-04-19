package com.example.h91;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.h91.Clases.Empleado;
import com.example.h91.Clases.EmpleadoViewHolder;

public class MostrarDetalleEmpleadoActivity extends AppCompatActivity {
    TextView txt_detalle_nombree=null;
    TextView txt_detalle_apellidoe=null;
    TextView txt_detalle_departamentoe=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_detalle_empleado);
        txt_detalle_nombree = findViewById(R.id.txt_detalle_nombree);
        txt_detalle_apellidoe= findViewById(R.id.txt_detalle_apellidoe);
        txt_detalle_departamentoe=findViewById(R.id.txt_detalle_departamentoe);
        Intent intent = getIntent();
        if(intent != null)
        {

            Empleado empleado= (Empleado) intent.getSerializableExtra(ActivityAnadirEmpleado.EXTRA_OBJETO_EMPLEADO);
            //txt_detalle_nombree.setText("el nombre es" );
            //txt_detalle_apellidoe.setText("el apellido es: ");
            //txt_detalle_departamentoe.setText("el departamento es: ");

            //RECIBE EL OBJETO EMPLEADO COMO NULL
            txt_detalle_nombree.setText(empleado.getNombre());
            txt_detalle_apellidoe.setText(empleado.getApellido());
            txt_detalle_departamentoe.setText("departamento" + empleado.getIdDepartamento());
            Log.i("empleado", "no se muestran los empleados por " + empleado);
        //ERRORES NO SE MUESTRA ESTE ACTIVITY
        }


    }
}