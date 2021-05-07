package com.example.h91;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.h91.Clases.Nominas;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class MostrarDetalleNominas extends AppCompatActivity implements Serializable {

    TextView txt_fechasubida=null;
    TextView txt_detalle_nombren=null;
    Button bt_descargarNomina=null;
    String url="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_detalle_nominas);

        txt_detalle_nombren=(TextView)findViewById(R.id.txt_detalle_nombren);
        txt_fechasubida=(TextView)findViewById(R.id.txt_fechasubida);
        bt_descargarNomina=(Button)findViewById(R.id.bt_descargarNomina);
        Intent intent= getIntent();
        if(intent!=null)
        {
            //Nominas n= (Nominas)intent.getSerializableExtra(Nominas.EXTRA_OBJETO_NOMINA);
            Nominas n= (Nominas)intent.getSerializableExtra(ActivityNominas.EXTRA_OBJETO_NOMINA);
            txt_detalle_nombren.setText(n.getNombre());
            txt_fechasubida.setText(n.getFecha_subida().toString());
            //txt_fechasubida.setText((CharSequence) n.getFecha_subida());

        }
    }

    public void DescargarNomina(View view) {
    url= "http://empleados.h91go.com/php/descargar_nomina.php?archivo=y7Ggm1R5ioLEn5eroKucmFV0oY/A";
        Uri uri= Uri.parse(url);
        Intent intent= new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

}
