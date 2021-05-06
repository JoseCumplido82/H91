package com.example.h91;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.h91.Clases.Departamento;
import com.example.h91.controladores.DepartamentoController;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ActivityBorrarDepartamento extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner sp_departamentoEliminar=null;
    Button bt_eliminarDepartamento=null;
    Button bt_volver22=null;
    Departamento deSeleccionado=null;
    ArrayAdapter<Departamento> adapter=null;
    ArrayList<Departamento> departamentos=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_departamento);
        sp_departamentoEliminar= (Spinner)findViewById(R.id.sp_departamentoEliminar);
        bt_eliminarDepartamento=(Button)findViewById(R.id.bt_eliminarDepartamento);
        bt_volver22=(Button)findViewById(R.id.bt_volver22);
        if(sp_departamentoEliminar!=null){
            sp_departamentoEliminar.setOnItemSelectedListener(this);
            departamentos= DepartamentoController.obtenerDepartamentos();
            if(departamentos!=null){
                adapter= new ArrayAdapter<Departamento>(this, R.layout.item_departamento, departamentos);
                sp_departamentoEliminar.setAdapter(adapter);
            }
        }
        //boton para volver a menu gestion plantilla
        bt_volver22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityGestionPlantilla.class);
                startActivityForResult(intent, 0);
            }
        });

    }



    public void mostrarToast(String mensaje)
    {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }


    public void BorrarDepartamento(View view) {
        AlertDialog.Builder alerta= new AlertDialog.Builder(this);
        alerta.setTitle("¿Desea borrar el departamento?");
        alerta.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(deSeleccionado==null)
                {
                    mostrarToast("selecciona un departamento");
                    return;
                }
                //borrar departamento
                boolean borradoOK= DepartamentoController.borrarDepartamento(deSeleccionado);
                if(borradoOK)
                {
                    mostrarToast("departamento borrado correctamente");
                    System.out.println("departamento borrado correctamente");
                    adapter.clear();
                    departamentos= DepartamentoController.obtenerDepartamentos();
                    adapter.addAll(departamentos);
                    finish();
                }
                else{
                    mostrarToast("el departamento no se pudo borrar");
                }
            }
        });
        alerta.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alerta.show();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Departamento d= (Departamento) sp_departamentoEliminar.getItemAtPosition(position);
        deSeleccionado= d;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}