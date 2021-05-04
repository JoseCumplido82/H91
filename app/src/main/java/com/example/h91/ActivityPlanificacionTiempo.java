package com.example.h91;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityPlanificacionTiempo extends AppCompatActivity {

    private String url;
    Button bt_verPlanificacion=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planificacion_tiempo);
        bt_verPlanificacion=(Button)findViewById(R.id.bt_verPlanificacion);

        //boton cerrar ausencias
        Button bt_volver18 = (Button) findViewById(R.id.bt_volver18);
        bt_volver18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent (v.getContext(), ActivityRRHH.class);
                startActivityForResult(intent3, 0);
            }
        });


        url="http://empleados.h91go.com/php/abrir_fichero.php?archivo=doc1.pdf";
        bt_verPlanificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(url);
                Intent intent= new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }


}