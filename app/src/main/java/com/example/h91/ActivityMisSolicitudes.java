package com.example.h91;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityMisSolicitudes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //para ocultar la barra de status
        getSupportActionBar().hide();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_solicitudes);

        //boton cerrar ausencias
        Button bt_volver = (Button) findViewById(R.id.bt_volver);
        bt_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent (v.getContext(), ActivitySolicitudes.class);
                startActivityForResult(intent3, 0);
            }
        });


    }
}