package com.example.h91;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActivityAyudaEmpleado extends AppCompatActivity {

    TextView txt_ayudaEmpleado;
    TextView txt_tituloAdyuda;
    TextView txt_textodeAyuda;
    String mensajeAyuda="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda_empleado);

        txt_ayudaEmpleado=(TextView)findViewById(R.id.txt_ayudaEmpleado);
        txt_tituloAdyuda=(TextView)findViewById(R.id.txt_tituloAyuda);
        txt_textodeAyuda=(TextView)findViewById(R.id.txt_textodeAyuda);


        mensajeAyuda="El funcionamiento de la aplicacion es el siguiente: \n\n En el menu principal nos encontramos con una pantalla de bienvenida para el empleado" +
                "dentro de esta pantalla de bienvenida, tenemos una serie de botones que usaremos para deplazarnos a cada una de las distintas opciones de las que dispone" +
                " la aplicacion. Pasamos a detallar cada una de ellas: \n -Boton Nominas: \n Dentro de este boton, el usuario tiene la posibilidad de visualizar todas las nominas" +
                "que tiene generadas, a medida que pasen los meses en la empresa el empleado irá generando mas nominas que iran incluyendose. \n -Boton Solicitudes: \n Dentro de este" +
                "boton, el usuario tendra la posibilidad de elegir que tipo de ausencia desea solicitar al personal de RRHH, dentro de estas opciones tendrá Vacaciones, Ausencias y" +
                " Tramites y tambien tendrá la posibilidad de poder visualizar el estado de estos tramites. \n -Boton Documentos: \n Dentro de este boton, el usuario tiene la posibilidad" +
                " de poder ver y descargar los distintos documentos que el personal de RRHH haya subido para que esten informados los empleados. \n -Boton Perfil Personal: " +
                " \n dentro de este boton, el usuario tiene la posibilidad de poder ver en detalle su perfil de empleado y poder modificar los datos de telefono, direccion y " +
                " correo electronico. \n -Boton Como llegar: \n Dentro de este boton el empleado tiene la posibilidad de poder ver donde está ubicada la empresa y en caso de " +
                "  no saber como llegar a la ubicacion se le calculará la ruta para que asi pueda llegar a la oficina sin ningún tipo de problema. ";

        txt_textodeAyuda.setText(mensajeAyuda);
        System.out.println(mensajeAyuda);

    }

    public void volverPrincipalMenu(View view) {
        finish();
    }
}