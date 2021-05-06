package com.example.h91;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityGestionPlantilla extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_plantilla);


        //boton ver empleados
        Button bt_verEmpleado= (Button)findViewById(R.id.bt_verEmpleado);
        bt_verEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), ActivityVerEmpleados.class);
                startActivityForResult(intent, 0);
            }
        });

        //boton borrar departamento
        Button bt_borrarDepartamento= (Button)findViewById(R.id.bt_borrarDepartamento);
        bt_borrarDepartamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(v.getContext(), ActivityBorrarDepartamento.class);
                startActivityForResult(intent,0);
            }
        });


        //boton añadir empleados

        Button bt_añadirEmpleado= (Button)findViewById(R.id.bt_añadirEmpleado);
        bt_añadirEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent (v.getContext(), ActivityAnadirEmpleado.class);
                startActivityForResult(intent2, 0);
            }
        });


        //boton borrar empleados
        Button bt_borrarEmpleado= (Button)findViewById(R.id.bt_borrarEmpleado);
        bt_borrarEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), ActivityBorrarEmpleado.class);
                startActivityForResult(intent, 0);
            }
        });

        //boton volver a panel RRHH
        Button bt_volver14 = (Button) findViewById(R.id.bt_volver14);
        bt_volver14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), ActivityRRHH.class);
                startActivityForResult(intent, 0);
            }
        });

        //boton crear departamento
        Button bt_insertarDepartamento = (Button) findViewById(R.id.bt_insertarDepartamento);
        bt_insertarDepartamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(v.getContext(),NuevoDepartamentoActivity.class);
                startActivityForResult(intent,0);
            }
        });

    }
}