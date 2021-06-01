package com.example.h91;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.h91.Clases.Empleado;
import com.example.h91.Clases.listaEmpleadosAdapter;
import com.example.h91.controladores.EmpleadoController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class ActivityMostrarDetalleSupervisor extends AppCompatActivity implements Serializable {
    TextView txt_detalle_nombree3=null;
    TextView txt_detalle_departamentoe2=null;
    TextView txt_detalleUsuarioe3=null;
    String nombreDpt="";
    Empleado empleado= new Empleado();
    private static final int PETICION1 = 1;
    private RecyclerView rv_empleados3;
    private listaEmpleadosAdapter empleadosAdapter;
    private ArrayList<Empleado> empleados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_detalle_supervisor);
        txt_detalle_nombree3= findViewById(R.id.txt_detalle_nombree3);
        txt_detalle_departamentoe2= findViewById(R.id.txt_detalle_departamentoe2);
        txt_detalleUsuarioe3= findViewById(R.id.txt_detalleUsuarioe3);
        Intent intent= getIntent();
        if(intent!=null)
        {
            Empleado empleado= (Empleado) intent.getSerializableExtra(ActivityAnadirEmpleado.EXTRA_OBJETO_EMPLEADO);

            if(empleado.getIdDepartamento()==1){
                nombreDpt= "Compras";
            }else if(empleado.getIdDepartamento()==2){
                nombreDpt="Ventas";
            }else if(empleado.getIdDepartamento()==3){
                nombreDpt="Financiero";
            }else if(empleado.getIdDepartamento()==4){
                nombreDpt="Marketplace";
            }else if(empleado.getIdDepartamento()==5){
                nombreDpt="Logistica";
            }else if(empleado.getIdDepartamento()==6){
                nombreDpt="Tienda";
            }else if(empleado.getIdDepartamento()==7){
                nombreDpt="Limpieza";
            }else if(empleado.getIdDepartamento()==8){
                nombreDpt="Diseño";
            }else if(empleado.getIdDepartamento()==9){
                nombreDpt="IT";
            }else if(empleado.getIdDepartamento()==10){
                nombreDpt="Promart";
            }else if(empleado.getIdDepartamento()==11){
                nombreDpt="Calidad";
            }else if(empleado.getIdDepartamento()==12){
                nombreDpt="Producto";
            }else if(empleado.getIdDepartamento()==13){
                nombreDpt="RRHH";
            }else if(empleado.getIdDepartamento()==18){
                nombreDpt="Desarrollo";
            }


            txt_detalle_nombree3.setText(empleado.getNombre());
            txt_detalle_departamentoe2.setText("DPTO. " + " " + empleado.getIdDepartamento() + " - " + nombreDpt);
            txt_detalleUsuarioe3.setText(empleado.getUsuario());



        }
        empleados = EmpleadoController.obtenerEmpleadosDepartamento();
        if (empleados != null) {
            rv_empleados3 = (RecyclerView) findViewById(R.id.rv_empleados3);
            empleadosAdapter = new listaEmpleadosAdapter(this, empleados);
            rv_empleados3.setAdapter(empleadosAdapter);
            rv_empleados3.setLayoutManager(new LinearLayoutManager(this));
            Log.i("supervisores", " supervisores recuperados");
        } else {
            Log.i("supervisores", " no pude recuperar los supervisores");
        }
        //--------------------------------------------------------------------
//------------------------------------------------------------
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
                ItemTouchHelper.DOWN | ItemTouchHelper.UP, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(empleados, from, to);
                empleadosAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.LEFT) {
                    mostrarToast("ha pulsado izquierda, supervisor oculto, si quiere eliminar dirijase a la pestaña eliminar supervisor");
                    empleados.remove(viewHolder.getAdapterPosition());
                    empleadosAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                }
                if (direction == ItemTouchHelper.RIGHT) {
                    mostrarToast("ha pulsado derecha, supervisor oculto, si quiere eliminar dirijase a la pestaña eliminar supervisor");
                    empleados.remove(viewHolder.getAdapterPosition());
                    empleadosAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

                }
            }
        });
        helper.attachToRecyclerView(rv_empleados3);

    }
    private void mostrarToast(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }
    public void CrearNotificacionEmpleado(View view) {
        Intent intentelegido = new Intent(this, ActivityMensajeNotificaciones.class);
        intentelegido.putExtra("referenciaEmpleado", txt_detalleUsuarioe3.getText().toString());
        startActivity(intentelegido);
    }

    public void CrearSancionEmpleado(View view) {
        Intent intent= new Intent(this, ActivitySancionarEmpleado.class);
        intent.putExtra("referenciaEmpleado", txt_detalleUsuarioe3.getText().toString());
        startActivity(intent);
    }

    public void EliminarEmpleado(View view) {
        Intent intent = new Intent(this, ActivityBorrarEmpleado.class);
        startActivity(intent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PETICION1) {
            if (resultCode == RESULT_OK) {
                Empleado e = (Empleado) data.getSerializableExtra(ActivityAnadirEmpleado.EXTRA_OBJETO_EMPLEADO);
                empleados.add(e);
                // Notify the adapter, that the data has changed.
                rv_empleados3.getAdapter().notifyItemInserted(empleados.size());
                // Scroll to the bottom.
                rv_empleados3.smoothScrollToPosition(empleados.size());
            }
        }
    }
}