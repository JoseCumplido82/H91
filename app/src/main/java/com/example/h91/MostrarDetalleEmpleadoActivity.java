package com.example.h91;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.h91.Clases.Empleado;

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
            Empleado e = (Empleado) intent.getSerializableExtra(NuevoEmpleadoActivity.EXTRA_OBJETO_EMPLEADO);
            txt_detalle_nombree.setText(e.getNombre());
            txt_detalle_apellidoe.setText(e.getApellido());
            txt_detalle_departamentoe.setText("departamento" + e.getIdDepartamento());

        //ERRORES NO SE MUESTRA ESTE ACTIVITY
        }

    }
}