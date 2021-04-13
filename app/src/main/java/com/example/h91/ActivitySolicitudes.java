package com.example.h91;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivitySolicitudes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitudes);


        //boton mostrar vacaciones
        Button bt_vacaciones= (Button)findViewById(R.id.bt_vacaciones);
        bt_vacaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent (v.getContext(), ActivityVacaciones.class);
                startActivityForResult(intent3, 0);
            }
        });

        //boton mostrar ausencias
        Button bt_ausencias= (Button)findViewById(R.id.bt_ausencias);
        bt_ausencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent (v.getContext(), ActivityAusencias.class);
                startActivityForResult(intent3, 0);
            }
        });

        //boton mostrar otras solicitudes
        Button bt_osolicitudes=(Button)findViewById(R.id.bt_osolicitudes);
        bt_osolicitudes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4= new Intent(v.getContext(), ActivityOtrasSolicitudes.class);
                startActivityForResult(intent4,0);
            }
        });

        //boton mostrar mis solicitudes
        Button bt_misSolicitudes=(Button)findViewById(R.id.bt_misSolicitudes);
        bt_misSolicitudes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4= new Intent(v.getContext(), ActivityMisSolicitudes.class);
                startActivityForResult(intent4,0);
            }
        });

        //boton cerrar solicitudes
        Button bt_volver7 = (Button) findViewById(R.id.bt_volver7);
        bt_volver7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent (v.getContext(), PanelEmpleado.class);
                startActivityForResult(intent2, 0);
                //finish();
            }
        });


    }
}