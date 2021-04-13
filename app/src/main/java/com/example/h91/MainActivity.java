package com.example.h91;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String url;
    private EditText nombreUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.bt_acceder);
        nombreUsuario= (EditText)findViewById(R.id.edt_usuario);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreRRHH= "jose";
                String nombreEmpleado = "andrei";
                if(nombreUsuario.getText().toString().equals(nombreRRHH)){
                    Intent intent = new Intent(v.getContext(), ActivityRRHH.class);
                    startActivityForResult(intent,0);
                    //Exportar nombre usuario
                    intent.putExtra("Bienvenido", nombreUsuario.getText().toString());
                    startActivityForResult(intent,0);
                }else if(nombreUsuario.getText().toString().equals(nombreEmpleado)){
                    Intent intent = new Intent(v.getContext(), PanelEmpleado.class);
                    startActivityForResult(intent,0);
                    //Exportar nombre usuario
                    intent.putExtra("Bienvenido", nombreUsuario.getText().toString());
                    startActivityForResult(intent,0);
                }




                //----------------------FUNCIONA-------------------------------------------
                //Intent intent = new Intent (v.getContext(), PanelEmpleado.class);
                //startActivityForResult(intent, 0);
                //Exportar nombre usuario
                //intent.putExtra("Bienvenido", nombreUsuario.getText().toString());
                //startActivityForResult(intent,0);
            }
        });

        //boton abrir informacion
        TextView txt_passolvidada=(TextView) findViewById(R.id.txt_passolvidada);
        url= "http://codea.app/";
        txt_passolvidada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri= Uri.parse(url);
                Intent intent= new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }
}