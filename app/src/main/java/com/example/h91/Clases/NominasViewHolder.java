package com.example.h91.Clases;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h91.MostrarDetalleNominas;
import com.example.h91.R;

//import static com.example.h91.ActivityNominas.EXTRA_OBJETO_NOMINA;
import static com.example.h91.Clases.Nominas.EXTRA_OBJETO_NOMINA;

public class NominasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txt_nombren=null;
    public TextView txt_fechasubidanomina=null;

    final listaNominasAdapter lcAdapter;


    public NominasViewHolder(@NonNull View itemView, listaNominasAdapter lcAdapter) {
        super(itemView);
        this.txt_nombren = (TextView)  itemView.findViewById(R.id.txt_nombren);
        this.txt_fechasubidanomina = (TextView)  itemView.findViewById(R.id.txt_fechasubidanomina);
        this.lcAdapter = lcAdapter;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int nPosition= getLayoutPosition();
        Nominas nominas= this.lcAdapter.getListaNominas().get(nPosition);
        Log.i("nominas", "has seleccionado " + nominas.toString());
        lcAdapter.notifyDataSetChanged();
        Intent intent= new Intent(lcAdapter.getC(), MostrarDetalleNominas.class);
        Nominas n1= new Nominas(nominas.getNombre(), nominas.getFecha_subida());
        intent.putExtra(EXTRA_OBJETO_NOMINA,n1);
        lcAdapter.getC().startActivity(intent);
    }
}
