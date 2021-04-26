package com.example.h91;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.h91.Clases.Empleado;
import com.example.h91.Clases.EmpleadoViewHolder;

import java.io.Serializable;

public class MostrarDetalleEmpleadoActivity extends AppCompatActivity implements Serializable{
    TextView txt_detalle_nombree=null;
    TextView txt_detalle_apellidoe=null;
    TextView txt_detalle_departamentoe=null;
    TextView txt_detalle_Correoe=null;
    TextView txt_detalleUsuarioe=null;
    TextView txt_detalleSancionese=null;
    String nombreDpt="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_detalle_empleado);
        txt_detalle_nombree = findViewById(R.id.txt_detalle_nombree);
        txt_detalle_apellidoe= findViewById(R.id.txt_detalle_apellidoe);
        txt_detalle_departamentoe=findViewById(R.id.txt_detalle_departamentoe);
        txt_detalle_Correoe=findViewById(R.id.txt_detalleCorreoe);
        txt_detalleUsuarioe=findViewById(R.id.txt_detalleUsuarioe);
        txt_detalleSancionese=findViewById(R.id.txt_detalleSancionese);
        Intent intent = getIntent();
        if(intent != null)
        {

            Empleado empleado= (Empleado) intent.getSerializableExtra(ActivityAnadirEmpleado.EXTRA_OBJETO_EMPLEADO);


            if(empleado.getIdDepartamento()==1){
                 nombreDpt= "Compras";
            }else if(empleado.getIdDepartamento()==2){
                 nombreDpt="Ventas";
            }else if(empleado.getIdDepartamento()==3){
                nombreDpt="Financiero";
            }else if(empleado.getIdDepartamento()==4){
                nombreDpt="Marketplace";
            }else if(empleado.getIdDepartamento()==5){
                nombreDpt="Logistica";
            }
            if(txt_detalle_nombree.getText().toString().isEmpty()){
                txt_detalle_nombree.setText("EMPLEADO: " + " "+ empleado.getNombre().toUpperCase());
            }
            if(txt_detalle_apellidoe.getText().toString().isEmpty()){
                txt_detalle_apellidoe.setText("APELLIDO: " + " " + empleado.getApellido().toUpperCase());

            }
          //  txt_detalle_nombree.setText("EMPLEADO: " + " "+ empleado.getNombre().toUpperCase());
           // txt_detalle_apellidoe.setText("APELLIDO: " + " " + empleado.getApellido().toUpperCase());
            txt_detalle_departamentoe.setText("DPTO. " + " " + empleado.getIdDepartamento() + " - " + nombreDpt);
            txt_detalle_Correoe.setText("Correo: " + empleado.getCorreo());
            txt_detalleUsuarioe.setText("DNI: " + empleado.getUsuario());
            //txt_detalleSancionese.setText("Sanciones: " + Integer.valueOf(empleado.getSanciones()));
            Log.i("empleado", "se muestran el empleado " + empleado);

        }





    }

    public void CrearNotificacionEmpleado(View view) {

        Intent intentelegido = new Intent(this, ActivityMensajeNotificaciones.class);
        intentelegido.putExtra("referenciaEmpleado", txt_detalleUsuarioe.getText().toString());
        startActivity(intentelegido);
    }

    public void CrearSancionEmpleado(View view) {
        Intent intent= new Intent(this, ActivitySancionarEmpleado.class);
        startActivity(intent);
    }

    public void EliminarEmpleado(View view) {
        Intent intent = new Intent(this, ActivityBorrarEmpleado.class);
        startActivity(intent);
    }
}