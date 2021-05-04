package com.example.h91;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.h91.Clases.Nominas;

import java.io.Serializable;
import java.util.Calendar;

public class MostrarDetalleNominas extends AppCompatActivity implements Serializable {

    TextView txt_fechasubida=null;
    TextView txt_detalle_nombren=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_detalle_nominas);

        txt_detalle_nombren=(TextView)findViewById(R.id.txt_detalle_nombren);
        txt_fechasubida=(TextView)findViewById(R.id.txt_fechasubida);
        Intent intent= getIntent();
        if(intent!=null)
        {
            //Nominas n= (Nominas)intent.getSerializableExtra(Nominas.EXTRA_OBJETO_NOMINA);
            Nominas n= (Nominas)intent.getSerializableExtra(ActivityNominas.EXTRA_OBJETO_NOMINA);
            txt_detalle_nombren.setText(n.getNombre());
            txt_fechasubida.setText((CharSequence) n.getFecha_subida());

        }
    }
}
