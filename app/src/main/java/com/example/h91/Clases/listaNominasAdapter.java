package com.example.h91.Clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h91.R;

import java.util.ArrayList;

public class listaNominasAdapter extends RecyclerView.Adapter<NominasViewHolder> {
    private Context c;
    private ArrayList<Nominas> listaNominas;
    private LayoutInflater nInflater;

    public listaNominasAdapter(Context c, ArrayList<Nominas> listaNominas) {
        this.c = c;
        this.listaNominas = listaNominas;
        nInflater= LayoutInflater.from(c);
    }

    public LayoutInflater getnInflater() {
        return nInflater;
    }

    public void setnInflater(LayoutInflater nInflater) {
        this.nInflater = nInflater;
    }

    public Context getC() {
        return c;
    }

    public void setC(Context c) {
        this.c = c;
    }

    public ArrayList<Nominas> getListaNominas() {
        return listaNominas;
    }

    public void setListaNominas(ArrayList<Nominas> listaNominas) {
        this.listaNominas = listaNominas;
    }


    @NonNull
    @Override
    public NominasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View nItemView= nInflater.inflate(R.layout.item_recyclerview_nominas, parent, false);
        return new NominasViewHolder(nItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull NominasViewHolder holder, int position) {
        Nominas nominaActual= listaNominas.get(position);
        holder.txt_nombren.setText("Nombre Nomina: " + nominaActual.getNombre());
        holder.txt_fechasubidanomina.setText("Fecha: "+ nominaActual.getFecha_subida());

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
