package com.example.h91;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityMensajeNotificaciones extends AppCompatActivity {

    EditText edt_asuntoSolicitud2=null;
    EditText edt_observacionesMensaje3=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje_notificaciones);
        edt_asuntoSolicitud2=(EditText) findViewById(R.id.edt_asuntoSolicitud2);
        edt_observacionesMensaje3=(EditText)findViewById(R.id.edt_observacionesMensaje3);
    }


    public void mostrarToast(String mensaje)
    {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }


    public void restablecerCampos(View view){
        edt_asuntoSolicitud2.setText("");
        edt_observacionesMensaje3.setText("");;
        mostrarToast("Los campos se han restablecido");


    }
}