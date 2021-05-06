package com.example.h91;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.h91.Clases.Empleado;
import com.example.h91.controladores.EmpleadoController;
import com.example.h91.modelos.ConfiguracionDB;

import java.io.Serializable;

public class PanelEmpleado extends AppCompatActivity{
    public static final String EXTRA_OBJETO_EMPLEADO= "empleado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //para ocultar la barra de status
        getSupportActionBar().hide();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_empleado);
        //importamos nombre de MainActivity
        Bundle bundle = getIntent().getExtras();
        //String nombreImportado= bundle.getString("Bienvenido");
      //  String txt_Bienvenido= nombreImportado;
        TextView out= (TextView) findViewById(R.id.txt_Bienvenido);
       // out.setText("Bienvenido " + txt_Bienvenido);
        //boton cerrar panel empleado
        Button bt_cerrar = (Button) findViewById(R.id.bt_cerrar);
        bt_cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent2 = new Intent (v.getContext(), MainActivity.class);
                //startActivityForResult(intent2, 0);
                //finish();
                Intent intent = new Intent(v.getContext(),MainActivity.class);
                startActivity(intent);
                //onBackPressed();
            }
        });

        //boton mostrar notificaciones
        Button bt_notificaciones= (Button)findViewById(R.id.bt_notificaciones);
        bt_notificaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent (v.getContext(), ActivityNotificaciones.class);
                startActivityForResult(intent3, 0);
            }
        });

        //boton mostrar nominas
        Button bt_nominas =(Button) findViewById(R.id.bt_nominas);
        bt_nominas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(v.getContext(), ActivityNominas.class);
                startActivityForResult(intent4,0);
            }
        });

        //boton mostrar documentos subidos
        Button bt_documentos = (Button) findViewById(R.id.bt_documentos);
        bt_documentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent6 = new Intent(v.getContext(),ActivityDocumentos.class);
                startActivityForResult(intent6,0);
            }
        });


        //boton mostrar solicitudes
        Button bt_solicitudes=(Button) findViewById(R.id.bt_solicitudes);
        bt_solicitudes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5= new Intent(v.getContext(), ActivitySolicitudes.class);
                startActivityForResult(intent5,0);
            }
        });

        //boton mostrar Perfil Personal
        Button bt_perfilEmpleado= (Button)findViewById(R.id.bt_perfilEmpleado);
        bt_perfilEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean EmpleadoOK= EmpleadoController.comprobarUserActual(ConfiguracionDB.UsuarioActual, ConfiguracionDB.PassActual);
               // Empleado empleado= new Empleado(ConfiguracionDB.UsuarioActual, ConfiguracionDB.PassActual);
                if(EmpleadoOK){
                   // Intent intent= new Intent(PanelEmpleado.this, ActivityPerfilEmpleado.class);
                    Intent intent= new Intent(PanelEmpleado.this, ActivityPerfilEmpleado.class);
                    intent.putExtra("empleado", EXTRA_OBJETO_EMPLEADO);
                    //setResult(RESULT_OK, intent);
                   // intent.putExtra(EXTRA_OBJETO_EMPLEADO, empleado);
                    startActivity(intent);
                }

            }
        });

        //boton ayuda empleado
        TextView txt_ayudaUsuario= (TextView)findViewById(R.id.txt_ayudaUsuario);
        txt_ayudaUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(PanelEmpleado.this, ActivityAyudaEmpleado.class);
                startActivity(intent);
            }
        });

        //boton como llegar a la oficina
        Button bt_comoLlegar=(Button)findViewById(R.id.bt_comoLlegar);
        bt_comoLlegar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String direccionMapa="Calle de Fuerteventura, 21, 28703 San Sebastián de los Reyes, Madrid";
                Uri uri=Uri.parse("geo:0,0?q=" + direccionMapa);
                Intent intent= new Intent(Intent.ACTION_VIEW, uri);
                if (intent.resolveActivity(getPackageManager()) == null) {
                    startActivity(intent);
                    System.out.println("cargando mapas");
                }
                else {
                    System.out.println("no se ha cargado el mapa");
                }
            }
        });
    }


}