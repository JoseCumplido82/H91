package com.example.h91;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.h91.modelos.ConfiguracionDB;

public class ActivityRRHH extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r_r_h_h);


        //boton gestion plantilla
        Button bt_gestionPlantilla= (Button)findViewById(R.id.bt_gestionPlantilla);
        bt_gestionPlantilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent (v.getContext(), ActivityGestionPlantilla.class);
                startActivityForResult(intent3, 0);
            }
        });

        //boton planificacion de tiempo
        Button bt_planificacionTiempo= (Button)findViewById(R.id.bt_planificacionTiempo);
        bt_planificacionTiempo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent (v.getContext(), ActivityPlanificacionTiempo.class);
                startActivityForResult(intent4, 0);
            }
        });

        //boton crear notificacion
        Button bt_crearNotificacion= (Button)findViewById(R.id.bt_crearNotificacion);
        bt_crearNotificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent (v.getContext(), ActivityCrearNotificacion.class);
                startActivityForResult(intent4, 0);
            }
        });

        //boton para ir al perfil personal del empleado de RRHH
        Button bt_perfilRRHH =(Button)findViewById(R.id.bt_perfilRRHH);
        bt_perfilRRHH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(v.getContext(), ActivityPerfilEmpleado.class);
                startActivityForResult(intent,0);
            }
        });


        //boton cerrar panel RRHH
        Button bt_cerrarSesionRRHH = (Button) findViewById(R.id.bt_cerrarSesionRRHH);
        bt_cerrarSesionRRHH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfiguracionDB.UsuarioActual="";
                ConfiguracionDB.PassActual="";
                Intent intent2 = new Intent (v.getContext(), MainActivity.class);
                startActivityForResult(intent2, 0);
            }
        });

        //boton cambiar contraseña RRHH
        Button bt_cambiarPassRRHH =(Button) findViewById(R.id.bt_cambiarPassRRHH);
        bt_cambiarPassRRHH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(v.getContext(), ActivityCambiarPass.class);
                startActivityForResult(intent,0);
            }
        });

    }
}