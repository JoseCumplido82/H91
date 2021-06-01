package com.example.h91;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.h91.Clases.Empleado;
import com.example.h91.Clases.Sanciones;
import com.example.h91.controladores.SancionesController;
import com.example.h91.modelos.EmpleadoDB;

public class ActivitySancionarEmpleado extends AppCompatActivity {

    Spinner sp_sancion;
    EditText edt_motivoSancion;
    TextView txt_dniEmpleadorecogido;
    String [] tipoSancion= null;
    ArrayAdapter<String> adapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sancionar_empleado);
        sp_sancion=(Spinner)findViewById(R.id.sp_sancion);
        edt_motivoSancion=(EditText)findViewById(R.id.edt_motivoSancion);
        txt_dniEmpleadorecogido=(TextView)findViewById(R.id.txt_dniEmpleadorecogido);
        tipoSancion= new String[]{"Leve", "Grave"};
        adapter= new ArrayAdapter<String>(this, R.layout.spinner_item_sancion, tipoSancion);
        sp_sancion.setAdapter(adapter);
        getIntent().getSerializableExtra("referenciaEmpleado");
        txt_dniEmpleadorecogido.setText((CharSequence) getIntent().getSerializableExtra("referenciaEmpleado"));
    }

    public void mostrarToast(String mensaje)
    {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void restablecerCampos(View view){
        edt_motivoSancion.setText("");
        mostrarToast("Los campos se han restablecido");
    }

    public void volverAPrincipal(View view){
        Intent intent= new Intent(this, ActivityRRHH.class);
        startActivity(intent);
    }

    public void SancionarEmpleado(View view) {
        AlertDialog.Builder alerta= new AlertDialog.Builder(this);
        alerta.setTitle("¿Estás seguro que desea insertar la sanción?");
        alerta.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(edt_motivoSancion.getText().toString().isEmpty()){
                    mostrarToast("introduce un motivo para la sancion");
                    edt_motivoSancion.setError("Introduce un motivo para la sancion");
                }
                Sanciones sanciones=null;
                try {
                    System.out.println("entra al try de sanciones");
                    System.out.println(txt_dniEmpleadorecogido.getText().toString());
                    Empleado empleado= (EmpleadoDB.buscarEmpleadoTabla(txt_dniEmpleadorecogido.getText().toString()));
                    System.out.println(empleado);
                    String spinnerselect= sp_sancion.getSelectedItem().toString();
                    System.out.println(spinnerselect);
                    sanciones= new Sanciones(empleado.getId(),spinnerselect,edt_motivoSancion.getText().toString());
                    System.out.println(sanciones);
                    System.out.println(txt_dniEmpleadorecogido);
                    boolean insertadoOK= SancionesController.insertarSancion(sanciones);
                    if(insertadoOK){
                        System.out.println("entra al if del insertado");
                        mostrarToast("sancion creada correctamente");
                        finish();
                    }
                    else {
                        System.out.println("no se pudo crear la sancion");
                    }
                }catch (Exception e){
                    mostrarToast("error, revilsa los datos");
                    Log.i("sanciones", "sancion no creada");
                }
            }
        });
        alerta.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                edt_motivoSancion.setText("");
                mostrarToast(" los campos se han reiniciado");
            }
        });
        alerta.show();

    }

}