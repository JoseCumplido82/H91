package com.example.h91;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityNotificaciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaciones);

        //boton para ir a email
        Button bt_irAnotificaciones = (Button) findViewById(R.id.bt_irAnotificaciones);
        bt_irAnotificaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(Intent.ACTION_VIEW);
                //intent.setClassName("com.google.android.gm", "com.google.android.gm.ConversationListActivityGmail");
                //startActivity(intent);
            }
        });


        //boton cerrar notificaciones
        Button bt_volverPrincipal = (Button) findViewById(R.id.bt_volverPrincipal);
        bt_volverPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent intent2 = new Intent (v.getContext(), PanelEmpleado.class);
               // startActivityForResult(intent2, 0);
                finish();
            }
        });


    }
}