package com.example.h91;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.h91.Clases.Empleado;
import com.example.h91.controladores.EmpleadoController;
import com.example.h91.modelos.ConfiguracionDB;
import com.example.h91.modelos.EmpleadoDB;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String url;
    public EditText nombreUsuario;
    public EditText edt_pass;
    //  No puese asignar valores aqui, lo tienes que hacer en el oncreate una vez que has utilizado el findviewbyid por eso falla la aplicacion

    boolean preferenciasGuardadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.bt_acceder);
        nombreUsuario= (EditText)findViewById(R.id.edt_usuario);
        edt_pass=(EditText)findViewById(R.id.edt_pass);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreRRHH = "50483599W";
                String pass = "Madrid2021";
                String nombreEmpleado = "andrei";


                String usuario = nombreUsuario.getText().toString();
                String password = edt_pass.getText().toString();

                ConfiguracionDB.UsuarioActual = usuario;
                ConfiguracionDB.PassActual = password;
                // String usuario ="X8450397J";
                //String password = "55diasenP";

                // comprobarUser(nombreUsuario.getText().toString(), edt_pass.getText().toString());

                //if (nombreUsuario.getText().toString().equals(EmpleadoDB.buscarEmpleadoTabla(ConfiguracionDB.UsuarioActual))) {
                    //Intent intent = new Intent(v.getContext(), ActivityRRHH.class);
                    //startActivityForResult(intent, 0);
                    //Exportar nombre usuario
                    //intent.putExtra("Bienvenido", nombreUsuario.getText().toString());

                    //COMPRUEBO EL USUARIO
                    //ConfiguracionDB.UsuarioActual = usuario;
                    //ConfiguracionDB.PassActual = password;

                  //  startActivityForResult(intent, 0);

                //} else if (nombreUsuario.getText().toString().equals(nombreEmpleado)) {
                    //Intent intent = new Intent(v.getContext(), PanelEmpleado.class);
                    //startActivityForResult(intent, 0);
                    //Exportar nombre usuario

                   // intent.putExtra("Bienvenido", nombreUsuario.getText().toString());
                    //ConfiguracionDB.UsuarioActual = usuario;
                    //ConfiguracionDB.PassActual = password;
                  //  startActivityForResult(intent, 0);
                //}
                boolean resultado = EmpleadoController.comprobarUserActual(ConfiguracionDB.UsuarioActual, ConfiguracionDB.PassActual);
                if (resultado == true) {
                   // Intent intent= new Intent(v.getContext(), PanelEmpleado.class);
                   // startActivity(intent);
                  ComprobarSiHayDatosEmpleado(ConfiguracionDB.UsuarioActual, ConfiguracionDB.PassActual);

                        mostrarToast("encontrado");
                        Log.i("sql", "encontrado");


                }else if(ConfiguracionDB.UsuarioActual.equals("mar")){
                    Intent intent= new Intent(v.getContext(), ActivityRRHH.class);
                    startActivity(intent);
                    mostrarToast("usuario encontrado");
                    Log.i("sql", "encontrado");
                }

                else {
                    mostrarToast("no encontrado");
                    Log.i("sql", "no encontrado");
                }

            }
        });

        //boton abrir informacion
        TextView txt_passolvidada=(TextView) findViewById(R.id.txt_passolvidada);
        //url= "http://codea.app/";
        txt_passolvidada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Uri uri= Uri.parse(url);
                //Intent intent= new Intent(Intent.ACTION_VIEW, uri);
                //startActivity(intent);
                Intent intent= new Intent(MainActivity.this, ActivityOlvisteLaPass.class);
                startActivity(intent);
            }
        });

    }


    private void mostrarToast(String encontrado) {
        Toast.makeText(this,"logeado",Toast.LENGTH_SHORT).show();
    }


    public boolean ComprobarSiHayDatosEmpleado(String dni, String pass){
        boolean EmpleadoEnTabla= EmpleadoDB.EmpleadoEnTabla(ConfiguracionDB.UsuarioActual, ConfiguracionDB.PassActual);
        String nombre = "";
        String apellido = "";
        String telefono = "";
        int codDepartamento=0;
            if (EmpleadoEnTabla == true) {
                Empleado e = (EmpleadoDB.buscarEmpleadoTabla(ConfiguracionDB.UsuarioActual));
                 nombre = e.getNombre();
                 apellido = e.getApellido();
                 telefono = e.getTelefono();
                 String NombredeEmpleado="";
                 codDepartamento=e.getIdDepartamento();
                 //if(!NombredeEmpleado.equals(nombre)){
               if (nombre==null) {
                    Intent intent = new Intent(this, ActivityRellenarDatosEmpleado.class);
                    startActivity(intent);
                }else if(codDepartamento==13){
                    Intent intent= new Intent(this, ActivityRRHH.class);
                    startActivity(intent);
               }

               else {
                    Intent intent = new Intent(this, PanelEmpleado.class);
                    startActivity(intent);
                    mostrarToast("encontrado");
                    Log.i("sql", "encontrado");
                }

            } else {
                System.out.println("no encontrado el empleado");
                return false;
            }
            return true;

    }


    //PARA CONTINUAR CON EL LOGIN DE USUARIO
    public void comprobarUser(String nombre, String pass){
        String nombreUser= String.valueOf(nombreUsuario.getText());
        String passUser= String.valueOf(edt_pass.getText());
        Empleado ComprobarEmpleado= new Empleado(nombreUser, passUser);
        //buscar empleado en tabla para ver si existe
      boolean ComprobadoOK=EmpleadoDB.EmpleadoEnTabla(ComprobarEmpleado.getUsuario(), ComprobarEmpleado.getPass());
      if(ComprobadoOK){
          System.out.println("existe empleado");
           Intent intent= new Intent(this, PanelEmpleado.class);
           startActivityForResult(intent,0);
      }else {
          System.out.println("no existe empleado");
          mostrarToast("Pos no te logeas");
      }
        Log.i("empleado recuperado" , "he recuperado el empleado " + ComprobarEmpleado.getUsuario() + " " + ComprobarEmpleado.getPass());

    }




}