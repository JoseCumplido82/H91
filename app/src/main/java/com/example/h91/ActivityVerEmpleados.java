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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.h91.Clases.Empleado;
import com.example.h91.Clases.listaEmpleadosAdapter;
import com.example.h91.controladores.EmpleadoController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ActivityVerEmpleados extends AppCompatActivity {

    private static final int PETICION1=1;
    private RecyclerView rv_empleados;
    private listaEmpleadosAdapter empleadosAdapter;
    private ArrayList<Empleado> empleados;
    private ImageView img_update=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_empleados);

        img_update=(ImageView)findViewById(R.id.img_update);
        empleados= EmpleadoController.obtenerEmpleados();
        if(empleados!=null){
            rv_empleados= (RecyclerView) findViewById(R.id.rv_empleados);
            empleadosAdapter = new listaEmpleadosAdapter(this, empleados);
            rv_empleados.setAdapter(empleadosAdapter);
            rv_empleados.setLayoutManager(new LinearLayoutManager(this));
            Log.i("empleados", " empleados recuperados");
        }else{
            Log.i("empleados", " no pude recuperar los empleados");
        }
        //------------------------------------------------------------
        ItemTouchHelper helper= new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT |
                ItemTouchHelper.DOWN | ItemTouchHelper.UP, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int from= viewHolder.getAdapterPosition();
                int to= target.getAdapterPosition();
                Collections.swap(empleados, from, to);
                empleadosAdapter.notifyItemMoved(from,to);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(direction == ItemTouchHelper.LEFT)
                {
                    mostrarToast("ha pulsado izquierda, empleado oculto, si quiere eliminar dirijase a la pestaña eliminar empleado");
                    empleados.remove(viewHolder.getAdapterPosition());
                    empleadosAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                }
                if(direction == ItemTouchHelper.RIGHT)
                {
                    mostrarToast("ha pulsado derecha, medicamento oculto, si quiere eliminar dirijase a la pestaña eliminar empleado");
                    empleados.remove(viewHolder.getAdapterPosition());
                    empleadosAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

                }
            }
        });

        helper.attachToRecyclerView(rv_empleados);

        //boton volver a panel RRHH
        Button bt_volver15 = (Button) findViewById(R.id.bt_volver15);
        bt_volver15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), ActivityGestionPlantilla.class);
                startActivityForResult(intent, 0);
            }
        });


    }
    private void mostrarToast(String texto) {
        Toast.makeText(this,texto, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PETICION1) {
            if (resultCode == RESULT_OK) {
                Empleado e = (Empleado) data.getSerializableExtra(ActivityAnadirEmpleado.EXTRA_OBJETO_EMPLEADO);
                empleados.add(e);
                // Notify the adapter, that the data has changed.
                rv_empleados.getAdapter().notifyItemInserted(empleados.size());
                // Scroll to the bottom.
                rv_empleados.smoothScrollToPosition(empleados.size());
            }
        }
    }
    public void nuevoEmpleado(View view){
        Intent intent= new Intent(this, ActivityAnadirEmpleado.class);
        startActivityForResult(intent, PETICION1);
    }
    public void refrescarEmpleados(View view){
        empleados= EmpleadoController.obtenerEmpleados();
        if(empleados!=null){
            empleadosAdapter.setListaEmpleados(empleados);
            rv_empleados.getAdapter().notifyDataSetChanged();
        }
    }

}