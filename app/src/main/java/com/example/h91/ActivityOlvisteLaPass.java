package com.example.h91;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.h91.Clases.Empleado;
import com.example.h91.controladores.EmpleadoController;
import com.example.h91.modelos.ConfiguracionDB;
import com.example.h91.modelos.EmpleadoDB;

import java.security.NoSuchAlgorithmException;

public class ActivityOlvisteLaPass extends AppCompatActivity {
    EditText edt_NombreUsuario;
    String passCifrada="";
    byte [] salt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olviste_la_pass);
        edt_NombreUsuario=(EditText)findViewById(R.id.edt_NombreUsuario);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void RestablecerPassOlvidada(View view) {
        try {


        try {
            if(edt_NombreUsuario.getText().toString().isEmpty()){
                edt_NombreUsuario.setError("Introduce tu usuario");
                mostrarToast("INTRODUCE TU USUARIO");
            }
            salt=ConfiguracionDB.getSalt();
            passCifrada=ConfiguracionDB.get_SHA_512_SecurePassword(ConfiguracionDB.pass,salt);
            String textosalt= ConfiguracionDB.saltToString(salt);

            Empleado empleado = (EmpleadoDB.buscarEmpleadoTabla(edt_NombreUsuario.getText().toString()));
            empleado = new Empleado(empleado.getId(), empleado.getIdDepartamento(), edt_NombreUsuario.getText().toString(),
                    ConfiguracionDB.get_SHA_512_SecurePassword(ConfiguracionDB.pass, salt),textosalt, empleado.getNombre(), empleado.getApellido(), empleado.getDomicilio(), empleado.getCorreo()
                    , empleado.getTelefono(), empleado.getFecha_incorporacion());
            boolean actualizadoOK = EmpleadoController.actualizarEmpleado(empleado);
            if (actualizadoOK) {
                mostrarToast("CONTRASEÑA RESETEADA POR DEFECTO, POR FAVOR CONTANTE CON RRHHH PARA CONOCER SU NUEVA CONTRASEÑA");
                System.out.println("CONTRASEÑA RESETEADA POR DEFECTO");
                System.out.println(passCifrada);
                finish();
                Intent intent = new Intent(ActivityOlvisteLaPass.this, MainActivity.class);
                startActivity(intent);
            } else {
                mostrarToast("CONTRASEÑA NO RESETEADA");
                System.out.println("CONTRASEÑA NO RESETEADA");
            }

        }catch (Exception e){
            mostrarToast("USUARIO NO ENCONTRADO");
            System.out.println("entra en el catch");
            edt_NombreUsuario.setError("Introduce un usuario correcto");
        }
        }catch (Exception ex){
            System.out.println("entra al primer try");
        }

    }

    private void mostrarToast(String encontrado) {

        Toast.makeText(this,encontrado,Toast.LENGTH_LONG).show();
    }


}