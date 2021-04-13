package com.example.h91;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityDocumentos extends AppCompatActivity {
    private String url;
    private String url2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentos);


        //boton abrir informacion
        Button bt_informacion=(Button) findViewById(R.id.bt_informacion);
        url= "http://codea.app/";
        bt_informacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri= Uri.parse(url);
                Intent intent= new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        //boton abrir plantillas
        Button bt_plantillas=(Button) findViewById(R.id.bt_plantillas);
        url2= "http://h91go.com";
        bt_plantillas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(url2);
                Intent intent= new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        //boton mostrar documentos subidos
        Button bt_docusubidos = (Button) findViewById(R.id.bt_docusubidos);
        bt_docusubidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent6 = new Intent(v.getContext(),ActivityDocumentosSubidos.class);
                startActivityForResult(intent6,0);
            }
        });


        //boton volver a anterior
        Button bt_volver01= (Button) findViewById(R.id.bt_volver01);
        bt_volver01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent12= new Intent(getApplicationContext(), PanelEmpleado.class);
                //startActivity(intent12);
                finish();
            }
        });
    }



}