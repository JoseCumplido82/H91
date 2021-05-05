package com.example.h91;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.h91.Clases.Empleado;
import com.example.h91.controladores.EmpleadoController;
import com.example.h91.modelos.ConfiguracionDB;
import com.example.h91.modelos.EmpleadoDB;

public class ActivityOlvisteLaPass extends AppCompatActivity {
    EditText edt_NombreUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olviste_la_pass);
        edt_NombreUsuario=(EditText)findViewById(R.id.edt_NombreUsuario);

    }

    public void RestablecerPassOlvidada(View view) {
        Empleado empleado= (EmpleadoDB.buscarEmpleadoTabla(edt_NombreUsuario.getText().toString()));
        empleado=new Empleado(empleado.getId(), empleado.getIdDepartamento(), edt_NombreUsuario.getText().toString(),
                ConfiguracionDB.pass, empleado.getNombre(), empleado.getApellido(), empleado.getDomicilio(), empleado.getCorreo()
                ,empleado.getTelefono(), empleado.getFecha_incorporacion());
        boolean actualizadoOK= EmpleadoController.actualizarEmpleado(empleado);
        if(actualizadoOK)
        {
         mostrarToast("CONTRASEÑA RESETEADA POR DEFECTO");
         System.out.println("CONTRASEÑA RESETEADA POR DEFECTO");
         finish();
         Intent intent= new Intent(ActivityOlvisteLaPass.this, MainActivity.class);
         startActivity(intent);
        }else {
            mostrarToast("CONTRASEÑA NO RESETEADA");
            System.out.println("CONTRASEÑA NO RESETEADA");
        }

    }

    private void mostrarToast(String encontrado) {
        Toast.makeText(this,"logeado",Toast.LENGTH_SHORT).show();
    }
}