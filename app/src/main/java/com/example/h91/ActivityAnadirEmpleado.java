package com.example.h91;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.h91.Clases.Departamento;
import com.example.h91.Clases.Empleado;
import com.example.h91.controladores.DepartamentoController;
import com.example.h91.controladores.EmpleadoController;
import com.example.h91.modelos.ConfiguracionDB;


import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ActivityAnadirEmpleado extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_OBJETO_EMPLEADO= "empleado";
    Spinner sp_departamento=null;
    EditText edt_dni =null;
    Departamento dseleccionado=null;
    ArrayAdapter<Departamento> adapter=null;
    ArrayList<Departamento> departamentos=null;
    EditText edt_fechaIncorporacion=null;
    String passCifrada="";

    public ActivityAnadirEmpleado() throws NoSuchAlgorithmException {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_empleado);

        edt_dni =(EditText) findViewById(R.id.edt_dni);
        edt_fechaIncorporacion= (EditText) findViewById(R.id.edt_fechaIncorporacion);
        sp_departamento = (Spinner)findViewById(R.id.sp_departamento);
        if(sp_departamento!=null){
            sp_departamento.setOnItemSelectedListener(this);
            departamentos = DepartamentoController.obtenerDepartamentos();
            if(departamentos!=null){
                adapter = new ArrayAdapter<Departamento>(this, R.layout.item_departamento, departamentos);
                sp_departamento.setAdapter(adapter);
            }



        }
        //boton cerrar añadir empleado
        Button bt_volver16 = (Button) findViewById(R.id.bt_volver16);
        bt_volver16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    public void mostrarToast(String mensaje)
    {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }


    public void restablecerCampos(View view){
        edt_dni.setText("");
        edt_fechaIncorporacion.setText("");
        mostrarToast("Los campos se han reiniciado");


    }

    public void insertarEmpleado(View view) {
        AlertDialog.Builder alerta1 = new AlertDialog.Builder(this);
        alerta1.setTitle("quieres guardar el empleado?");
        alerta1.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(dseleccionado == null)
                {
                    mostrarToast("selecciona un departamento");
                    return;
                }


                Empleado em = null;
                try{

                    try {
                        passCifrada=ConfiguracionDB.get_SHA_512_SecurePassword(ConfiguracionDB.pass, ConfiguracionDB.salt);
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                    System.out.println(passCifrada);
                    String salt= String.valueOf(ConfiguracionDB.getSalt());
                    String usuario= String.valueOf(edt_dni.getText());
                    String fechatexto= String.valueOf(edt_fechaIncorporacion.getText());
                    Date fechaIncorporacion2=new SimpleDateFormat("yyyy-mm-dd").parse(fechatexto);
                    em = new Empleado(dseleccionado.getId(), usuario, passCifrada,fechaIncorporacion2);
                    Log.i("recoge", "recoge" + " " + em);
                    //insertar Empleado
                    boolean insertadoOK = EmpleadoController.InsertarEmpleado(em);
                    if(insertadoOK)
                    {
                        mostrarToast("empleado insertado correctamente");
                        finish();
                    }
                    else{
                        mostrarToast("no se pudo crear el empleado");
                    }

                }catch (Exception e)
                {
                    mostrarToast("error, revisa los datos introducidos");
                }

            }
        });

        alerta1.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                edt_dni.setText("");
                edt_fechaIncorporacion.setText("");
                mostrarToast("Los campos se han reiniciado");
            }
        });
        alerta1.show();
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Departamento d = (Departamento) sp_departamento.getItemAtPosition(position);
        dseleccionado = d;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}