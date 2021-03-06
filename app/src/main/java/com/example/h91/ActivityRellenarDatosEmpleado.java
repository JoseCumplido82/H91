package com.example.h91;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.h91.Clases.Empleado;
import com.example.h91.controladores.EmpleadoController;
import com.example.h91.modelos.ConfiguracionDB;
import com.example.h91.modelos.EmpleadoDB;

import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class ActivityRellenarDatosEmpleado extends AppCompatActivity {

    EditText edt_NombreEmpleado=null;
    EditText edt_ApellidosEmpleado=null;
    EditText edt_DomicilioEmpleado=null;
    EditText edt_EmailEmpleado=null;
    EditText edt_TelefonoEmpleado=null;
    Button bt_guardarDatosEmpleado=null;
    Button bt_restablecerDatosEmpleado=null;
    EditText edt_password1=null;
    EditText edt_password2=null;
    String passCifrada="";
    byte salt[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rellenar_datos_empleado);
        edt_NombreEmpleado= (EditText)findViewById(R.id.edt_NombreEmpleado);
        edt_ApellidosEmpleado=(EditText)findViewById(R.id.edt_ApellidosEmpleado);
        edt_DomicilioEmpleado=(EditText)findViewById(R.id.edt_DomicilioEmpleado);
        edt_EmailEmpleado=(EditText)findViewById(R.id.edt_EmailEmpleado);
        edt_TelefonoEmpleado=(EditText)findViewById(R.id.edt_TelefonoEmpleado);
        bt_guardarDatosEmpleado=(Button)findViewById(R.id.bt_guardarDatosEmpleado);
        bt_restablecerDatosEmpleado=(Button)findViewById(R.id.bt_restablecerDatosEmpleado);
        edt_password1=(EditText)findViewById(R.id.edt_password1);
        edt_password2=(EditText)findViewById(R.id.edt_password2);
    }

    public void RestablecerDatosEmpleado(View view){
        edt_NombreEmpleado.setText("");
        edt_ApellidosEmpleado.setText("");
        edt_DomicilioEmpleado.setText("");
        edt_TelefonoEmpleado.setText("");
        edt_EmailEmpleado.setText("");
        edt_password1.setText("");
        edt_password2.setText("");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void GuardarDatosEmpleado(View view) throws NoSuchAlgorithmException {
     String nombre= edt_NombreEmpleado.getText().toString();
     String apellidos= edt_ApellidosEmpleado.getText().toString();
     String domicilio= edt_DomicilioEmpleado.getText().toString();
     String telefono= edt_TelefonoEmpleado.getText().toString();
     String email= edt_EmailEmpleado.getText().toString();
     String pass1= edt_password1.getText().toString();
     String pass2= edt_password2.getText().toString();
     String dni= ConfiguracionDB.UsuarioActual;
     boolean EmpleadoEnTabla= EmpleadoDB.EmpleadoEnTabla(dni, ConfiguracionDB.PassActual);
     if(EmpleadoEnTabla) {
            Empleado empleado= (EmpleadoDB.buscarEmpleadoTabla(dni));

         try {
             salt=ConfiguracionDB.getSalt();
         } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
             noSuchAlgorithmException.printStackTrace();
         }
         passCifrada=ConfiguracionDB.get_SHA_512_SecurePassword(pass1, salt);
         String textosalt= ConfiguracionDB.saltToString(salt);
         try {
             if (edt_password1.getText().toString().isEmpty()||edt_password2.getText().toString().isEmpty()){
                 edt_password1.setError("introduce una contrase??a");
                 edt_password2.setError("introduce una contrase??a");
             }
             else{

             if (edt_password1.getText().toString().equals(edt_password2.getText().toString()) && validarEmail(email)) {

                 empleado = new Empleado(empleado.getId(),empleado.getIdDepartamento(), dni, passCifrada, textosalt, nombre, apellidos, domicilio, email, telefono, empleado.getFecha_incorporacion());

                 Log.i("Datos del empleado", empleado.toString());
                 boolean actualidadook = EmpleadoController.actualizarEmpleado(empleado);
                 if (actualidadook) {
                     mostrarToast("EMPLEADO ACTUALIZADO CORRECTAMENTE");
                     System.out.println("Empleado actualizado " + empleado.toString());
                     // finish();
                     Intent intent= new Intent(ActivityRellenarDatosEmpleado.this, MainActivity.class);
                     startActivity(intent);
                 } else {
                     mostrarToast("EMPLEADO NO ACTUALIZADO ");
                     System.out.println("Empleado no actualizado " + empleado.toString());
                 }
             }
             else {
                 if(!edt_password1.getText().toString().equals(edt_password2.getText().toString())){
                     edt_password1.setError("las contrase??as no coinciden");
                     edt_password2.setError("Las contrase??as no coinciden ");
                 }else {
                     edt_EmailEmpleado.setError("direccion de correo no valida");
                 }

             }}
         }catch (Exception e){
             System.out.println("entra al catch al no a??adirle contrase??a");
         }




         }
    }

    private void mostrarToast(String encontrado) {
        Toast.makeText(this,encontrado,Toast.LENGTH_SHORT).show();
    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }


}