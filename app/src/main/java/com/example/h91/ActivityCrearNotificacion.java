package com.example.h91;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityCrearNotificacion extends AppCompatActivity {

    CheckBox cb_seleccionar;
    CheckBox checkBox2;
    TextView txt_nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_notificacion);

        cb_seleccionar=(CheckBox)findViewById(R.id.cb_seleccionar);
        checkBox2=(CheckBox)findViewById(R.id.checkBox2);
        txt_nombre=(TextView)findViewById(R.id.txt_nombre);
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