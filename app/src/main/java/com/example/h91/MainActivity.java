package com.example.h91;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.h91.Clases.Empleado;
import com.example.h91.controladores.EmpleadoController;
import com.example.h91.modelos.EmpleadoDB;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String url;
    public EditText nombreUsuario;
    public EditText edt_pass;
   // String preferencias1 = nombreUsuario.getText().toString();
   // String preferencias2= edt_pass.getText().toString();
    //boolean preferenciasGuardadas;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
       //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);







        Button btn = (Button) findViewById(R.id.bt_acceder);
        nombreUsuario= (EditText)findViewById(R.id.edt_usuario);
        edt_pass=(EditText)findViewById(R.id.edt_pass);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreRRHH= "mar";
                String nombreEmpleado = "andrei";
         comprobarUser(nombreUsuario.getText().toString(), edt_pass.getText().toString());

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




    //METODO PARA GUARDAR LA CONFIGURACION DEL USUARIO
 //   public void guardarConfiguracionUser(){
   //     SharedPreferences preferences= getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
     //   SharedPreferences.Editor editor= preferences.edit();
       // editor.putString("DniUser", nombreUsuario.getText().toString());
       // editor.putString("PassUser", edt_pass.getText().toString());
        //editor.commit();
   // }

    //METODO PARA CARGAR LA CONFIGURACION DEL USUARIO
    //public void cargarConfiguracionUser(){
      //  SharedPreferences preferences= getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
       // SharedPreferences.Editor editor= preferences.edit();
       // nombreUsuario.setText(preferences.getString("DniUser", nombreUsuario.getText().toString()));
        //edt_pass.setText(preferences.getString("Passuser", edt_pass.getText().toString()));
    //}

    //PARA CONTINUAR CON EL LOGIN DE USUARIO
    public void comprobarUser(String nombre, String pass){
        String nombreUser= String.valueOf(nombreUsuario.getText());
        String passUser= String.valueOf(edt_pass.getText());
        Empleado ComprobarEmpleado= new Empleado(nombreUser, passUser);
        //buscar empleado en tabla para ver si existe
      boolean ComprobadoOK=EmpleadoDB.EmpleadoEnTabla(ComprobarEmpleado.getUsuario(), ComprobarEmpleado.getPass());
      if(ComprobadoOK){
          System.out.println("existe empleado");
      }else {
          System.out.println("no existe empleado");

      }
        Log.i("empleado recuperado" , "he recuperado el empleado " + ComprobarEmpleado.getUsuario() + " " + ComprobarEmpleado.getPass());

    }




    public String getUser(String usuario){
       usuario= nombreUsuario.getText().toString();
        return usuario;
    }

    public String  getPass(String pass){
        pass=edt_pass.getText().toString();
        return pass;
    }
}