package com.example.h91;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.h91.Clases.Empleado;
import com.example.h91.controladores.EmpleadoController;
import com.example.h91.modelos.ConfiguracionDB;
import com.example.h91.modelos.EmpleadoDB;

import java.security.NoSuchAlgorithmException;

public class ActivityCambiarPass extends AppCompatActivity {

    EditText edt_cambiarPass1=null;
    EditText edt_cambiarPass2=null;
    Button bt_cambiarPass2=null;
    Button bt_cancelarCambio=null;
    String passCifrada="";
    byte[] salt;

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

    public void GuardarContrase├▒aEmpleado(View view) {
        AlertDialog.Builder alerta= new AlertDialog.Builder(this);
        alerta.setTitle("┬┐Desea guardar estos cambios?");
        alerta.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String pass= edt_cambiarPass1.getText().toString();

                String dni= ConfiguracionDB.UsuarioActual;

                boolean EmpleadoEnTabla= EmpleadoDB.EmpleadoEnTabla(dni,ConfiguracionDB.PassActual);
                if(EmpleadoEnTabla)
                {
                    Empleado empleado= (EmpleadoDB.buscarEmpleadoTabla(dni));
                    try {
                        salt=ConfiguracionDB.getSalt();
                    } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                        noSuchAlgorithmException.printStackTrace();
                    }
                    passCifrada=ConfiguracionDB.get_SHA_512_SecurePassword(pass, salt);
                    String textosalt= ConfiguracionDB.saltToString(salt);

                    if(edt_cambiarPass1.getText().toString().equals(edt_cambiarPass2.getText().toString())){
                        empleado= new Empleado(empleado.getId(), empleado.getIdDepartamento(), dni, passCifrada,textosalt, empleado.getNombre(), empleado.getApellido(), empleado.getDomicilio(),
                                empleado.getCorreo(),empleado.getTelefono(), empleado.getFecha_incorporacion());
                        boolean actualizadoOK= EmpleadoController.actualizarEmpleado(empleado);
                        if(actualizadoOK)
                        {
                            System.out.println(empleado.getPass());
                            System.out.println(empleado);
                            mostrarToast("CONTRASE├ĹA ACTUALIZADA CORRECTAMENTE");
                            System.out.println("CONTRASE├ĹA ACTUALIZADA");
                            finish();
                            Intent intent= new Intent(ActivityCambiarPass.this, MainActivity.class);
                            startActivity(intent);
                        }else
                        {
                            mostrarToast("CONTRASE├ĹA NO ACTUALIZADA");
                            System.out.println("CONTRASE├ĹA NO ACTUALIZADA");
                        }
                    }else {
                        if(!edt_cambiarPass1.getText().toString().equals(edt_cambiarPass2.getText().toString())){
                            edt_cambiarPass1.setError("las contrase├▒as no coinciden");
                            edt_cambiarPass2.setError("las contrase├▒as no coinciden");
                            mostrarToast("las contrase├▒as deben ser id├ęnticas");
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
        Toast.makeText(this,encontrado,Toast.LENGTH_SHORT).show();
    }

    public void volverAMenuEmplea(View view) {
        finish();
    }
}