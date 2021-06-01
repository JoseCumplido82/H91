package com.example.h91;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityVerPlantilla extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_plantilla);



        //boton ver empleados
        Button bt_verEmpleado2= (Button)findViewById(R.id.bt_verEmpleado2);
        bt_verEmpleado2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), ActivityVerEmpleados.class);
                startActivityForResult(intent, 0);
            }
        });
        //boton ver supervisores
        Button bt_VerSupervisor3= (Button)findViewById(R.id.bt_VerSupervisor3);
        bt_VerSupervisor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), VerSupervisores.class);
                startActivityForResult(intent, 0);
            }
        });

        //boton volver a panel RRHH
        Button bt_volver29 = (Button) findViewById(R.id.bt_volver29);
        bt_volver29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityGestionPlantilla.class);
                startActivityForResult(intent, 0);
            }
        });



    }


}