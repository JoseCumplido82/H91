package com.example.h91;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
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

public class MainActivity extends AppCompatActivity {
    private String url;
    Empleado empleado;
    public EditText nombreUsuario;
    public EditText edt_pass;
    String claveCifrada = "";
    byte[] salt;

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
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                String usuario = nombreUsuario.getText().toString();
                String password = edt_pass.getText().toString();

                ConfiguracionDB.UsuarioActual = usuario;
                ConfiguracionDB.PassActual = password;



                 empleado= EmpleadoDB.buscarEmpleadoTabla(ConfiguracionDB.UsuarioActual);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    salt=ConfiguracionDB.saltFromString(empleado.getSalt());
                }

                claveCifrada =ConfiguracionDB.get_SHA_512_SecurePassword(ConfiguracionDB.PassActual,salt);
                boolean resultado = false;
                System.out.println("clave generada con el pass recogido y salt" + claveCifrada);
                resultado = EmpleadoController.comprobarUserActual(ConfiguracionDB.UsuarioActual, claveCifrada);
                System.out.println("clave primera :" + claveCifrada);
                if (resultado == true) {
                    System.out.println(claveCifrada);
                    ComprobarSiHayDatosEmpleado(ConfiguracionDB.UsuarioActual, claveCifrada);
                    System.out.println("imprimir despues del comprobar datos empleado: " + claveCifrada);
                        mostrarToast("USUARIO CORRECTO ->" + ConfiguracionDB.UsuarioActual);
                        Log.i("sql", "encontrado");


              //  }else if(ConfiguracionDB.UsuarioActual.equals("mar")){
                //    String claveGenerada="";
                 //   Intent intent= new Intent(v.getContext(), ActivityRRHH.class);
                  //  startActivity(intent);
                  //  mostrarToast("USUARIO CORRECTO ->" + ConfiguracionDB.UsuarioActual);
                  //  Log.i("sql", "encontrado");
                }

                else {
                    mostrarToast("USUARIO INCORRECTO");
                    Log.i("sql", "no encontrado");
                }

            }
        });

        //boton abrir informacion
        TextView txt_passolvidada=(TextView) findViewById(R.id.txt_passolvidada);
        txt_passolvidada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, ActivityOlvisteLaPass.class);
                startActivity(intent);
            }
        });

    }


    private void mostrarToast(String encontrado) {
        Toast.makeText(this,encontrado,Toast.LENGTH_LONG).show();
    }


    public boolean ComprobarSiHayDatosEmpleado(String dni, String pass) {
        System.out.println("clave dentro del metodo comprobar si hay datos del empleado :" + claveCifrada);
        boolean EmpleadoEnTabla= EmpleadoDB.EmpleadoEnTabla(ConfiguracionDB.UsuarioActual, claveCifrada);
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
                    mostrarToast("USUARIO CORRECTO ->" + ConfiguracionDB.UsuarioActual);
                    Log.i("sql", "encontrado");
                }

            } else {
                System.out.println("no encontrado el empleado");
                return false;
            }
            return true;

    }





}