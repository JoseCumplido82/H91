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
import android.widget.Toast;

import com.example.h91.Clases.Empleado;
import com.example.h91.Clases.Tramites;
import com.example.h91.Clases.listaEmpleadosAdapter;
import com.example.h91.Clases.listaTramitesAdapter;
import com.example.h91.controladores.TramitesController;
import com.example.h91.modelos.ConfiguracionDB;

import java.util.ArrayList;
import java.util.Collections;

public class ActivityMisSolicitudes extends AppCompatActivity {

    private static final int PETICION1=1;
    private RecyclerView rv_tramites;
    private listaTramitesAdapter tramitesAdapterAdapter;
    private ArrayList<Tramites> tramites;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //para ocultar la barra de status
        getSupportActionBar().hide();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_solicitudes);
//------------------------------------------------------------------
       // Empleado empleado= new Empleado(ConfiguracionDB.UsuarioActual, ConfiguracionDB.PassActual);
       // int idEmpleado= empleado.getId();
        boolean tramiteok= TramitesController.obtenerIDempleadoTramite(ConfiguracionDB.IDUsuarioActual);
        if (tramiteok){
            tramites = TramitesController.obtenerTramites(ConfiguracionDB.IDUsuarioActual);
            if(tramites!=null){
                rv_tramites=(RecyclerView)findViewById(R.id.rv_tramites);
                tramitesAdapterAdapter= new listaTramitesAdapter(this, tramites);
                rv_tramites.setAdapter(tramitesAdapterAdapter);
                rv_tramites.setLayoutManager(new LinearLayoutManager(this));
            }else {
                Log.i("tramites", "no pude recuperar los tramites");
                System.out.println("no pude recuperar los tramites");
            }
        }

        //-------------------------------------------------------------------------
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
                ItemTouchHelper.DOWN | ItemTouchHelper.UP, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(tramites, from, to);
                tramitesAdapterAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(direction == ItemTouchHelper.LEFT)
                {
                    mostrarToast("has eliminado el tramite");
                    Tramites t = tramites.get(viewHolder.getAdapterPosition());
                    TramitesController.borrarTramites(t);
                    tramites.remove(viewHolder.getAdapterPosition());
                    tramitesAdapterAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                }
                if(direction == ItemTouchHelper.RIGHT)
                {
                    mostrarToast("ha pulsado derecha, tramite oculto, si quiere eliminar pulse izquierda");
                    tramites.remove(viewHolder.getAdapterPosition());
                    tramitesAdapterAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

                }
            }
        });
        helper.attachToRecyclerView(rv_tramites);

        //boton cerrar ausencias
        Button bt_volver = (Button) findViewById(R.id.bt_volver);
        bt_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent (v.getContext(), ActivitySolicitudes.class);
                startActivityForResult(intent3, 0);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PETICION1) {
            if (resultCode == RESULT_OK) {
                Tramites t = (Tramites) data.getSerializableExtra(ActivityOtrasSolicitudes.EXTRA_OBJETO_SOLICITUD);
                tramites.add(t);
                // Notify the adapter, that the data has changed.
                rv_tramites.getAdapter().notifyItemInserted(tramites.size());
                // Scroll to the bottom.
                rv_tramites.smoothScrollToPosition(tramites.size());
            }
        }
    }

    public void refrescarTramites(View view){
        tramites= TramitesController.obtenerTramites(ConfiguracionDB.IDUsuarioActual);
        if(tramites!=null){
            tramitesAdapterAdapter.setListaTramites(tramites);
            rv_tramites.getAdapter().notifyDataSetChanged();
        }
    }

    private void mostrarToast(String texto) {
        Toast.makeText(this,texto, Toast.LENGTH_SHORT).show();
    }
}