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
import java.util.Collections;

public class VerSupervisores extends AppCompatActivity {
    private static final int PETICION1 = 1;
    private RecyclerView rv_supervisores;
    private listaEmpleadosAdapter empleadosAdapter;
    private ArrayList<Empleado> empleados;
    private ImageView img_update2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_supervisores);

        img_update2 = (ImageView) findViewById(R.id.img_update2);
        empleados = EmpleadoController.obtenerSupervisores();
        if (empleados != null) {
            rv_supervisores = (RecyclerView) findViewById(R.id.rv_supervisores);
            empleadosAdapter = new listaEmpleadosAdapter(this, empleados);
            rv_supervisores.setAdapter(empleadosAdapter);
            rv_supervisores.setLayoutManager(new LinearLayoutManager(this));
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
        helper.attachToRecyclerView(rv_supervisores);

        //boton volver a panel RRHH
        Button bt_volver24 = (Button) findViewById(R.id.bt_volver24);
        bt_volver24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityVerPlantilla.class);
                startActivityForResult(intent, 0);
            }
        });


    }

    private void mostrarToast(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PETICION1) {
            if (resultCode == RESULT_OK) {
                Empleado e = (Empleado) data.getSerializableExtra(ActivityAnadirEmpleado.EXTRA_OBJETO_EMPLEADO);
                empleados.add(e);
                // Notify the adapter, that the data has changed.
                rv_supervisores.getAdapter().notifyItemInserted(empleados.size());
                // Scroll to the bottom.
                rv_supervisores.smoothScrollToPosition(empleados.size());
            }
        }
    }

    public void refrescarSupervisores(View view){
        empleados= EmpleadoController.obtenerSupervisores();
        if(empleados!=null){
            empleadosAdapter.setListaEmpleados(empleados);
            rv_supervisores.getAdapter().notifyDataSetChanged();
        }
    }

}