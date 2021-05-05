package com.example.h91;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.h91.Clases.Departamento;
import com.example.h91.Clases.Empleado;
import com.example.h91.controladores.DepartamentoController;
import com.example.h91.controladores.EmpleadoController;

import java.util.ArrayList;

public class NuevoDepartamentoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //public static final String EXTRA_OBJETO_DEPARTAMENTO="departamento";
    Spinner sp_supervisor=null;
    Empleado eseleccionado=null;
    ArrayAdapter<Empleado> adapter=null;
    ArrayList<Empleado> empleados=null;
    EditText edt_nombred=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_departamento);
        edt_nombred= findViewById(R.id.edt_nombred);
        sp_supervisor=(Spinner) findViewById(R.id.sp_supervisor);
        if(sp_supervisor!=null){
            sp_supervisor.setOnItemSelectedListener(this);
            empleados= EmpleadoController.obtenerEmpleados();
            if(empleados!=null){
                adapter= new ArrayAdapter<Empleado>(this, R.layout.item_departamento, empleados);
                sp_supervisor.setAdapter(adapter);
            }
        }
    }


    public void insertarDepartamento(View view) {
        String nombred= String.valueOf(edt_nombred.getText());
        if(nombred.isEmpty())
        {
            edt_nombred.setError("escribe un nombre para el departamento");
            return;
        }
        int idDelSupervisor= eseleccionado.getId();

        AlertDialog.Builder alerta1= new AlertDialog.Builder(this);
        alerta1.setTitle("¿Desea guardar el departamento?");
        alerta1.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Departamento d= new Departamento(idDelSupervisor, nombred);

                boolean insercionOK= DepartamentoController.insertarDepartamento(d);
                mostrarToast(insercionOK);
                Log.i("recoge", "recoge " + "id responsable " + d.getIdResponsable() + " nombre " + d.getNombre()+ " id " + d.getId());
                Log.i("recoge del empleado", "id empleado " + eseleccionado.getId() + " usuario "+ eseleccionado.getUsuario() + " valor recogido" + idDelSupervisor);
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
        Empleado e = (Empleado) sp_supervisor.getItemAtPosition(position);
        eseleccionado = e;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void mostrarToast(boolean insercionOK)
    {
        if(insercionOK)
        {
            Toast.makeText(this,"departamento guardado correctamente", Toast.LENGTH_SHORT).show();
            finish();
        }
        else
        {
            Toast.makeText(this,"No se pudo guardar el departamento", Toast.LENGTH_SHORT).show();
        }
    }
}