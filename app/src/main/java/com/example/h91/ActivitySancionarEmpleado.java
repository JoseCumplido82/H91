package com.example.h91;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ActivitySancionarEmpleado extends AppCompatActivity {

    Spinner sp_sancion;
    EditText edt_motivoSancion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sancionar_empleado);
        sp_sancion=(Spinner)findViewById(R.id.sp_sancion);
        edt_motivoSancion=(EditText)findViewById(R.id.edt_motivoSancion);


        String [] tipoSancion= new String[]{"Leve", "Grave"};
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipoSancion);
        sp_sancion.setAdapter(adapter);

    }

    public void mostrarToast(String mensaje)
    {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void restablecerCampos(View view){
        edt_motivoSancion.setText("");

        mostrarToast("Los campos se han restablecido");


    }

    public void volverAPrincipal(View view){
        Intent intent= new Intent(this, ActivityRRHH.class);
        startActivity(intent);
    }

}