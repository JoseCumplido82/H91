package com.example.h91;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class ActivityAyudaEmpleado extends AppCompatActivity {

    TextView txt_ayudaEmpleado;
    TextView txt_tituloAdyuda;
    TextView txt_textodeAyuda;
    String mensajeAyuda="";
    final StyleSpan letraEnNegrita= new StyleSpan(android.graphics.Typeface.BOLD); // Para hacer negrita
   // final SpannableStringBuilder texto= new SpannableStringBuilder("Tu texto");
    int colorRojo= R.color.colorRojo;
    String BotonNomina="-Boton Nomina: ";
    final SpannableStringBuilder texto= new SpannableStringBuilder(BotonNomina);
    String boton="hola";
    String BotonSolicitud="-Boton Solicitudes: ";
    String BotonDocumentos="-Boton Documentos: ";
    String BotonPerfilPersonal="-Boton Perfil Personal: ";
    String BotonComollegar="-Boton Como Llegar: ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda_empleado);

        txt_ayudaEmpleado=(TextView)findViewById(R.id.txt_ayudaEmpleado);
        txt_tituloAdyuda=(TextView)findViewById(R.id.txt_tituloAyuda);
        txt_textodeAyuda=(TextView)findViewById(R.id.txt_textodeAyuda);
        //txt_textodeAyuda.setTextColor(Color.GRAY);
        //cambiarcolor();
        texto.setSpan(letraEnNegrita, 0, 8, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        txt_textodeAyuda.setText(texto);
        mensajeAyuda="El funcionamiento de la aplicacion es el siguiente: \n\n En el menu principal nos encontramos con una pantalla de bienvenida para el empleado" +
                "dentro de esta pantalla de bienvenida, tenemos una serie de botones que usaremos para deplazarnos a cada una de las distintas opciones de las que dispone" +
                " la aplicacion. Pasamos a detallar cada una de ellas:"+ "\n\n" +  BotonNomina + " \n Dentro de este boton, el usuario tiene la posibilidad de visualizar todas las nominas" +
                " que tiene generadas, a medida que pasen los meses en la empresa el empleado irá generando mas nominas que iran incluyendose.\n\t Si pulsamos una de las nominas" +
                " entraremos dentro de esa nomina para poder verla en más detalle y tener la posibilidad de descargarla \n\n -Boton Solicitudes: \n Dentro de este" +
                "boton, el usuario tendra la posibilidad de elegir que tipo de ausencia desea solicitar al personal de RRHH, dentro de estas opciones tendrá Vacaciones, Ausencias y" +
                " Tramites y tambien tendrá la posibilidad de poder visualizar el estado de estos tramites.\n\t Si pulsase un tramite en especifico" +
                " cuando se encuentre en la ventana de mis solicitudes, podrá observar en detalle ese tramite solicitado y tendrá la posibilidad de poder cancelar" +
                " ese tramite si lo desease. \n\n -Boton Documentos: \n Dentro de este boton, el usuario tiene la posibilidad" +
                " de poder ver y descargar los distintos documentos que el personal de RRHH haya subido para que esten informados los empleados. \n\n -Boton Perfil Personal: " +
                " \n Dentro de este boton, el usuario tiene la posibilidad de poder ver en detalle su perfil de empleado y poder modificar los datos de telefono, direccion y " +
                " correo electronico.\n\t Si deseas cambiar la contraseña tan solo tendras que pulsar el botón cambiar contraseña y podrás cambiar la contraseña" +
                " introduciendo dos veces la nueva contraseña que deseas poner para poder entrar con tu usuario." +
                " \n\n -Boton Como llegar: \n Dentro de este boton el empleado tiene la posibilidad de poder ver donde está ubicada la empresa y en caso de " +
                " no saber como llegar a la ubicacion se le calculará la ruta para que asi pueda llegar a la oficina sin ningún tipo de problema. ";

        txt_textodeAyuda.setText(mensajeAyuda);
        //System.out.println(mensajeAyuda);

    }
    public void cambiarcolor(){

    }

    public void volverPrincipalMenu(View view) {
        finish();
    }
}