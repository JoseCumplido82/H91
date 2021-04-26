package com.example.h91;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.h91.Clases.Departamento;
import com.example.h91.Clases.Empleado;
import com.example.h91.controladores.EmpleadoController;
import com.example.h91.modelos.ConfiguracionDB;
import com.example.h91.modelos.EmpleadoDB;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class ActivityPerfilEmpleado extends AppCompatActivity {


    TextView txt_dni1=null;
    TextView txt_departamento1=null;
    TextView txt_nombre1=null;
    TextView txt_apellidos1=null;
    EditText txt_domicilio1= null;
    EditText txt_correo1=null;
    EditText txt_telefono1=null;
    EditText txt_fechaIncorpo1=null;
    Button bt_cambiarPass=null;
    Button bt_volverAtras=null;
    Button bt_guardarEmpleado=null;
    TextView txt_nombredpo=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //para ocultar la barra de status
        getSupportActionBar().hide();



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_empleado);
        txt_dni1=(TextView)findViewById(R.id.txt_dni1);
        txt_departamento1=(TextView)findViewById(R.id.txt_departamento1);
        txt_nombre1=(TextView)findViewById(R.id.txt_nombre1);
        txt_apellidos1=(TextView)findViewById(R.id.txt_apellidos1);
        txt_domicilio1=(EditText)findViewById(R.id.txt_domicilio1);
        txt_correo1=(EditText)findViewById(R.id.txt_correo1);
        txt_telefono1=(EditText)findViewById(R.id.txt_telefono1);
        txt_fechaIncorpo1=(EditText)findViewById(R.id.txt_fechaIncorpo1);
        bt_cambiarPass=(Button)findViewById(R.id.bt_cambiarPass);
        bt_volverAtras=(Button)findViewById(R.id.bt_volverAtras);
        txt_nombredpo=(TextView)findViewById(R.id.txt_nombredpo);
        bt_guardarEmpleado=(Button)findViewById(R.id.bt_guardarEmpleado);
        String nombreDpt="";


           boolean EmpleadoEnTabla= EmpleadoDB.EmpleadoEnTabla(ConfiguracionDB.UsuarioActual, ConfiguracionDB.PassActual);
           if(EmpleadoEnTabla){
              Empleado e= (EmpleadoDB.buscarEmpleadoTabla(ConfiguracionDB.UsuarioActual));
                String dni= e.getUsuario();
                txt_dni1.setText(dni);
                String nombre= e.getNombre();
                txt_nombre1.setText(nombre);
                String apellido= e.getApellido();
                txt_apellidos1.setText(apellido);

                    int departamentoNombre=e.getIdDepartamento();
                       if(e.getIdDepartamento()==1){
                           nombreDpt= "Compras";
                       }else if(e.getIdDepartamento()==2){
                           nombreDpt="Ventas";
                       }else if(e.getIdDepartamento()==3){
                           nombreDpt="Financiero";
                       }else if(e.getIdDepartamento()==4){
                           nombreDpt="Marketplace";
                       }else if(e.getIdDepartamento()==5){
                           nombreDpt="Logistica";
                       }
                txt_departamento1.setText(String.valueOf(departamentoNombre));
                txt_nombredpo.setText(nombreDpt);
                String correo= e.getCorreo();
                txt_correo1.setText(correo);
                String telefono=e.getTelefono();
                txt_telefono1.setText(telefono);
                String domicilio= e.getDomicilio();
                txt_domicilio1.setText(domicilio);
                txt_fechaIncorpo1.setText(e.getFecha_incorporacion().toString());
                txt_fechaIncorpo1.setFocusable(false);
               txt_fechaIncorpo1.setEnabled(false);


           }



    }

    private void mostrarToast(String encontrado) {
        Toast.makeText(this,"logeado",Toast.LENGTH_SHORT).show();
    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }


    public void guardar_cambios(View view){
        boolean EmpleadoEnTabla= EmpleadoDB.EmpleadoEnTabla(ConfiguracionDB.UsuarioActual, ConfiguracionDB.PassActual);
        if(EmpleadoEnTabla){
            Empleado e= (EmpleadoDB.buscarEmpleadoTabla(ConfiguracionDB.UsuarioActual));

            if(!txt_correo1.getText().equals("")||!txt_domicilio1.getText().equals("")||!txt_telefono1.equals("")){
                if(validarEmail(txt_correo1.getText().toString())){



                     e= new Empleado(e.getId(),Integer.valueOf((String) txt_departamento1.getText()), txt_dni1.getText().toString(), ConfiguracionDB.PassActual, txt_nombre1.getText().toString(),
                            txt_apellidos1.getText().toString(), txt_domicilio1.getText().toString(), txt_correo1.getText().toString(),
                            txt_telefono1.getText().toString(),e.getFecha_incorporacion());

                    Log.i("Datos del empleado", e.toString());
                   boolean actualidadook= EmpleadoController.actualizarEmpleado(e);
                   if(actualidadook){
                       mostrarToast("EMPLEADO ACTUALIZADO CORRECTAMENTE");
                       System.out.println("Empleado actualizado " + e.toString());
                       finish();
                   }else {
                       mostrarToast("EMPLEADO NO ACTUALIZADO ");
                       System.out.println("Empleado no actualizado " + e.toString());
                   }

                }else{
                    txt_correo1.setError("Direccion de correo no válida");
                    System.out.println("error con el correo no valido");
                }
            }else{
                mostrarToast("Debe rellenar todos los 3 campos");
                System.out.println("error al rellenar los campos");
            }


        }
        else{
            mostrarToast("no se ha encontrado el empleado");
        }
    }
}