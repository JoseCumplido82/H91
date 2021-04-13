package com.example.h91;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.h91.Clases.Departamento;
import com.example.h91.Clases.Empleado;
import com.example.h91.controladores.DepartamentoController;
import com.example.h91.controladores.EmpleadoController;

import java.io.Serializable;
import java.util.ArrayList;

public class NuevoEmpleadoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_OBJETO_EMPLEADO="empleado";
    Spinner sp_nuevoe_departamento =null;
    Departamento dseleccionado=null;
    ArrayAdapter<Departamento> adapter=null;
    ArrayList<Departamento> departamentos= null;
    EditText edt_nuevoe_nombre=null;

    EditText edt_nuevoe_apellido=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_empleado);
        edt_nuevoe_nombre = findViewById(R.id.edt_nuevoe_nombre);

        edt_nuevoe_apellido = findViewById(R.id.edt_nuevoe_apellido);
        sp_nuevoe_departamento = (Spinner)findViewById(R.id.sp_nuevoe_departamento);
        if(sp_nuevoe_departamento!=null){
            sp_nuevoe_departamento.setOnItemSelectedListener(this);
            departamentos = DepartamentoController.obtenerDepartamentos();
            if(departamentos!=null){
                adapter = new ArrayAdapter<Departamento>(this, R.layout.item_departamento, departamentos);
                sp_nuevoe_departamento.setAdapter(adapter);
            }
        }

    }
    public void mostrarToast(String mensaje)
    {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void insertarEmpleado(View view){
        AlertDialog.Builder alerta1= new AlertDialog.Builder(this);
        alerta1.setTitle("¿Quieres guardar el empleado?");
        alerta1.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(dseleccionado == null)
                {
                    mostrarToast("selecciona un departamento");
                    return;
                }
                Empleado empleado = null;
                try {
                    String nombre= String.valueOf(edt_nuevoe_nombre.getText());
                    String apellido= String.valueOf(edt_nuevoe_apellido.getText());
                    empleado = new Empleado(dseleccionado.getId(), nombre, apellido);

                }catch (Exception e)
                {
                    mostrarToast("error, revisa los datos introducidos");
                }
                //insertar empleado
                boolean insertadoOK= EmpleadoController.InsertarEmpleado(empleado);
                if(insertadoOK)
                {
                    mostrarToast("medicamento insertado correctamente");
                    Intent intent = new Intent();
                    //fallo aqui
                    intent.putExtra(EXTRA_OBJETO_EMPLEADO, (Serializable) empleado);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else{
                    mostrarToast("no se pudo crear el empleado");
                }
            }
        });
        alerta1.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alerta1.show();
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Departamento d = (Departamento) sp_nuevoe_departamento.getItemAtPosition(position);
        dseleccionado = d;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}