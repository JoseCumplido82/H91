package com.example.h91;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.h91.Clases.Empleado;
import com.example.h91.Clases.Tramites;
import com.example.h91.controladores.TramitesController;
import com.example.h91.modelos.ConfiguracionDB;

import java.io.Serializable;

public class MostrarDetalleSolicitud extends AppCompatActivity implements Serializable {
    TextView txt_detalleSolicitud=null;
    TextView txt_nombre_documento=null;
    TextView txt_asunto3=null;
    TextView txt_comentario=null;
    TextView txt_fecha_solicitud=null;
    TextView txt_idEstado=null;
    Button bt_gestionar=null;
    String nombreEstadoTramite="";
    Tramites tramites=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_detalle_solicitud);
        txt_detalleSolicitud= (TextView) findViewById(R.id.txt_detalleSolicitud);
        txt_nombre_documento=(TextView)findViewById(R.id.txt_nombre_documento);
        txt_asunto3=(TextView)findViewById(R.id.txt_asunto3);
        txt_comentario=(TextView)findViewById(R.id.txt_comentario);
        txt_fecha_solicitud=(TextView)findViewById(R.id.txt_fecha_solicitud);
        txt_idEstado=(TextView)findViewById(R.id.txt_idEstado);
        bt_gestionar=(Button)findViewById(R.id.bt_gestionar);
        Intent intent = getIntent();
        if(intent != null){


            tramites=(Tramites)intent.getSerializableExtra(ActivityOtrasSolicitudes.EXTRA_OBJETO_SOLICITUD);


            if(tramites.getIdEstado()==1){
                txt_idEstado.setBackgroundColor(Color.YELLOW);
                nombreEstadoTramite= "En proceso";
            }else if(tramites.getIdEstado()==2){
                txt_idEstado.setBackgroundColor(Color.GREEN);
                nombreEstadoTramite= "Aprobado";
            }else if(tramites.getIdEstado()==3){
                txt_idEstado.setBackgroundColor(Color.RED);
                nombreEstadoTramite= "Cancelado";
            }else if(tramites.getIdEstado()==4){
                txt_idEstado.setBackgroundColor(Color.BLACK);
                nombreEstadoTramite= "Denegado";
            }else if(tramites.getIdEstado()==5){
                txt_idEstado.setBackgroundColor(Color.BLUE);
                nombreEstadoTramite= "Finalizado";
            }

            txt_idEstado.setText( nombreEstadoTramite);
            txt_nombre_documento.setText(tramites.getNombre_documento());
            txt_asunto3.setText(tramites.getAsunto());
            txt_comentario.setText(tramites.getComentario());
            txt_fecha_solicitud.setText((CharSequence) tramites.getFecha_solicitud());





        }
    }
    //enviar a url de gestion de tramites
    public void enviarAGestionarTramite(View view) {
        //Intent intent= new Intent(this, ActivitySancionarEmpleado.class);
        //startActivity(intent);
        int estado= 3;
        txt_idEstado.setText("Cancelado");
        txt_idEstado.setBackgroundColor(Color.RED);
        tramites = new Tramites(ConfiguracionDB.IDUsuarioActual, txt_nombre_documento.getText().toString(),txt_asunto3.getText().toString(), tramites.getFecha_solicitud(), estado);
        boolean actualizadoOK= TramitesController.actualizarTramites(tramites);
        System.out.println(tramites);
        if(actualizadoOK){
            mostrarToast("TRAMITE CANCELADO CORRECTAMENTE");
            System.out.println("TRAMITE CANCELADO CORRECTAMENTE");
            finish();
        }else {
            mostrarToast("NO SE HA PODIDO CANCELAR LA SOLICITUD");
            System.out.println("NO SE HA PODIDO CANCELAR LA SOLICITUD");
            finish();
        }
    }
    private void mostrarToast(String encontrado) {
        Toast.makeText(this,"logeado",Toast.LENGTH_SHORT).show();
    }
}