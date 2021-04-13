package com.example.h91;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityDocumentosSubidos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentos_subidos);
        RecyclerView rv= (RecyclerView) findViewById(R.id.rv_documentos);

        //boton cerrar documentos subidos
        Button bt_volver13 = (Button) findViewById(R.id.bt_volver13);
        bt_volver13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent (v.getContext(), PanelEmpleado.class);
                startActivityForResult(intent3, 0);
            }
        });
    }
}