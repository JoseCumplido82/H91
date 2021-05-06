package com.example.h91;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.h91.Clases.Empleado;
import com.example.h91.controladores.EmpleadoController;
import com.example.h91.modelos.ConfiguracionDB;
import com.example.h91.modelos.EmpleadoDB;

public class ActivityCambiarPass extends AppCompatActivity {

    EditText edt_cambiarPass1=null;
    EditText edt_cambiarPass2=null;
    Button bt_cambiarPass2=null;
    Button bt_cancelarCambio=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_pass);
        edt_cambiarPass1=(EditText) findViewById(R.id.edt_cambiarPass1);
        edt_cambiarPass2=(EditText) findViewById(R.id.edt_cambiarPass2);
        bt_cambiarPass2=(Button)findViewById(R.id.bt_cambiarPass2);
        bt_cancelarCambio=(Button)findViewById(R.id.bt_cancelarCambio);
    }

    public void GuardarContraseñaEmpleado(View view) {
        AlertDialog.Builder alerta= new AlertDialog.Builder(this);
        alerta.setTitle("¿Desea guardar estos cambios?");
        alerta.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String dni= ConfiguracionDB.UsuarioActual;
                String pass= edt_cambiarPass1.getText().toString();
                boolean EmpleadoEnTabla= EmpleadoDB.EmpleadoEnTabla(dni,ConfiguracionDB.PassActual);
                if(EmpleadoEnTabla)
                {
                    Empleado empleado= (EmpleadoDB.buscarEmpleadoTabla(dni));
                    if(edt_cambiarPass1.getText().toString().equals(edt_cambiarPass2.getText().toString())){
                        empleado= new Empleado(empleado.getId(), empleado.getIdDepartamento(), dni, pass, empleado.getNombre(), empleado.getApellido(), empleado.getDomicilio(),
                                empleado.getCorreo(),empleado.getTelefono(), empleado.getFecha_incorporacion());
                        boolean actualizadoOK= EmpleadoController.actualizarEmpleado(empleado);
                        if(actualizadoOK)
                        {
                            mostrarToast("CONTRASEÑA ACTUALIZADA CORRECTAMENTE");
                            System.out.println("CONTRASEÑA ACTUALIZADA");
                            finish();
                            Intent intent= new Intent(ActivityCambiarPass.this, MainActivity.class);
                            startActivity(intent);
                        }else
                        {
                            mostrarToast("CONTRASEÑA NO ACTUALIZADA");
                            System.out.println("CONTRASEÑA NO ACTUALIZADA");
                        }
                    }else {
                        if(!edt_cambiarPass1.getText().toString().equals(edt_cambiarPass2.getText().toString())){
                            edt_cambiarPass1.setError("las contraseñas no coinciden");
                            edt_cambiarPass2.setError("las contraseñas no coinciden");
                            mostrarToast("las contraseñas deben ser idénticas");
                            edt_cambiarPass1.setText("");
                            edt_cambiarPass2.setText("");
                        }
                    }
                }
            }
        });
        alerta.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alerta.show();


    }

    private void mostrarToast(String encontrado) {
        Toast.makeText(this,"logeado",Toast.LENGTH_SHORT).show();
    }

    public void volverAMenuEmplea(View view) {
        finish();
    }
}