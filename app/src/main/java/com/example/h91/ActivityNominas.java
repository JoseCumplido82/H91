package com.example.h91;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.h91.Clases.Nominas;
import com.example.h91.Clases.listaNominasAdapter;
import com.example.h91.controladores.NominasController;
import com.example.h91.modelos.ConfiguracionDB;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class ActivityNominas extends AppCompatActivity {

    //public static final String EXTRA_OBJETO_NOMINA= "nominas";
    private static final int PETICION1 = 1;
    private RecyclerView rv_nominas;
    private listaNominasAdapter nominasAdapterAdapter;
    private ArrayList<Nominas> nominas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nominas);
        int dni=ConfiguracionDB.IDUsuarioActual;
        //-----------------------------------------------------------
        nominas = NominasController.obtenerNominas(dni);
        if(nominas != null) {
            rv_nominas = (RecyclerView) findViewById(R.id.rv_nominas);
            // Create an adapter and supply the data to be displayed.
            nominasAdapterAdapter = new listaNominasAdapter(this, nominas);
            // Connect the adapter with the RecyclerView.
            rv_nominas.setAdapter(nominasAdapterAdapter);
            // Give the RecyclerView a default layout manager.
            rv_nominas.setLayoutManager(new LinearLayoutManager(this));
        }
        else{
            Log.i("nominas", "no pude recuperar las nominas");
        }
        //------------------------------------------------------------
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
                ItemTouchHelper.DOWN | ItemTouchHelper.UP, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(nominas, from, to);
                nominasAdapterAdapter.notifyItemMoved(from, to);
                return true;
            }



            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(direction == ItemTouchHelper.LEFT)
                {
                    mostrarToast("has eliminado la nomina");
                    Nominas n = nominas.get(viewHolder.getAdapterPosition());
                    NominasController.borrarNominas(n);
                    nominas.remove(viewHolder.getAdapterPosition());
                    nominasAdapterAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                }
                if(direction == ItemTouchHelper.RIGHT)
                {
                    mostrarToast("ha pulsado derecha, nomina oculta, si quiere eliminarla pulse izquierda");
                    nominas.remove(viewHolder.getAdapterPosition());
                    nominasAdapterAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

                }
            }
        });
        helper.attachToRecyclerView(rv_nominas);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PETICION1) {
            if (resultCode == RESULT_OK) {
                Nominas c = (Nominas) data.getSerializableExtra(Nominas.EXTRA_OBJETO_NOMINA);
                nominas.add(c);
                // Notify the adapter, that the data has changed.
                rv_nominas.getAdapter().notifyItemInserted(nominas.size());
                // Scroll to the bottom.
                rv_nominas.smoothScrollToPosition(nominas.size());
            }
        }
    }


    private void mostrarToast(String texto) {
        Toast.makeText(this,texto, Toast.LENGTH_SHORT).show();
    }

    public void refrescarNominas(View view) {
        int id=ConfiguracionDB.IDUsuarioActual;
        nominas = NominasController.obtenerNominas(id);

        if(nominas != null) {
            nominasAdapterAdapter.setListaNominas(nominas);
            rv_nominas.getAdapter().notifyDataSetChanged();
        }
    }

}