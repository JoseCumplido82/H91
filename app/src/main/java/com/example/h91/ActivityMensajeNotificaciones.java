package com.example.h91;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.h91.Clases.Empleado;
import com.example.h91.modelos.EmpleadoDB;

public class ActivityMensajeNotificaciones extends AppCompatActivity {

    EditText edt_asuntoSolicitud2=null;
    EditText edt_observacionesMensaje3=null;
    TextView txt_dniUsuario=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje_notificaciones);
        edt_asuntoSolicitud2=(EditText) findViewById(R.id.edt_asuntoSolicitud2);
        edt_observacionesMensaje3=(EditText)findViewById(R.id.edt_observacionesMensaje3);
        txt_dniUsuario=(TextView)findViewById(R.id.txt_dniUsuario);
        getIntent().getSerializableExtra("referenciaEmpleado");
        txt_dniUsuario.setText((CharSequence) getIntent().getSerializableExtra("referenciaEmpleado"));
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

    public void EnviarNotificacionEmpleado(View view) {

        String asunto=edt_asuntoSolicitud2.getText().toString();
        String observaciones= edt_observacionesMensaje3.getText().toString();
        String dni= txt_dniUsuario.getText().toString();

                if(edt_asuntoSolicitud2.getText().toString().isEmpty()){
                    mostrarToast("introduce un asunto para la notificacion");
                    edt_asuntoSolicitud2.setError("Introduce un motivo para la notificación");
                }
                    Empleado empleado= (EmpleadoDB.buscarEmpleadoTabla(txt_dniUsuario.getText().toString()));

                    Intent intent= new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_EMAIL, new String(empleado.getCorreo()));
                    intent.putExtra(Intent.EXTRA_SUBJECT, asunto);
                    intent.putExtra(Intent.EXTRA_TEXT, observaciones);

                    intent.setType("message/rfc822");

                    startActivity(Intent.createChooser(intent, "Elige un cliente de correo"));

    }

    public void volverAnotificicaciones(View view) {
        Intent intent= new Intent(this, ActivityRRHH.class);
        startActivity(intent);
    }
}