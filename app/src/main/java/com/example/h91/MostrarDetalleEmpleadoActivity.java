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
import com.example.h91.Clases.Sanciones;
import com.example.h91.modelos.SancionesDB;

import java.io.Serializable;
import java.util.ArrayList;

public class MostrarDetalleEmpleadoActivity extends AppCompatActivity implements Serializable{
    TextView txt_detalle_nombree=null;
    TextView txt_detalle_apellidoe=null;
    TextView txt_detalle_departamentoe=null;
    TextView txt_detalle_Correoe=null;
    TextView txt_detalleUsuarioe=null;
    TextView txt_detalleSancionese=null;
    String nombreDpt="";
   // Sanciones sanciones=new Sanciones();
    Empleado empleado= new Empleado();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //para ocultar la barra de status
        getSupportActionBar().hide();

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
            }else if(empleado.getIdDepartamento()==6){
                nombreDpt="Tienda";
            }else if(empleado.getIdDepartamento()==7){
                nombreDpt="Limpieza";
            }else if(empleado.getIdDepartamento()==8){
                nombreDpt="Diseño";
            }else if(empleado.getIdDepartamento()==9){
                nombreDpt="IT";
            }else if(empleado.getIdDepartamento()==10){
                nombreDpt="Promart";
            }else if(empleado.getIdDepartamento()==11){
                nombreDpt="Calidad";
            }else if(empleado.getIdDepartamento()==12){
                nombreDpt="Producto";
            }else if(empleado.getIdDepartamento()==13){
                nombreDpt="RRHH";
            }else if(empleado.getIdDepartamento()==18){
                nombreDpt="Desarrollo";
            }

            txt_detalle_nombree.setText(empleado.getNombre());
            txt_detalle_apellidoe.setText(empleado.getApellido());
            //txt_detalle_departamentoe.setText("DPTO. " + " " + empleado.getIdDepartamento() + " - " + nombreDpt);
            txt_detalle_departamentoe.setText("DPTO. " + " " + empleado.getIdDepartamento() + " - " + nombreDpt);
            txt_detalle_Correoe.setText("Correo: " + empleado.getCorreo());
            txt_detalleUsuarioe.setText(empleado.getUsuario());
           //ArrayList<Sanciones> SancionesObtenidas=SancionesDB.obtenerSanciones();
           // Sanciones sanciones=new Sanciones();
           // System.out.println(SancionesObtenidas);
          //  if(!sanciones.getObservacion().isEmpty()) {
            //    System.out.println("entra en el if de sanciones " + sanciones.getObservacion());
             //   txt_detalleSancionese.setText("Empleado sancionado");
            //}else{
             //   txt_detalleSancionese.setText("No tiene sanciones");
              //  System.out.println("entra en el else de sanciones " +sanciones.getObservacion());
           // }

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
        intent.putExtra("referenciaEmpleado", txt_detalleUsuarioe.getText().toString());
        startActivity(intent);
    }

    public void EliminarEmpleado(View view) {
        Intent intent = new Intent(this, ActivityBorrarEmpleado.class);
        startActivity(intent);
    }
}