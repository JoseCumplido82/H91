package com.example.h91.Clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h91.R;

import java.util.ArrayList;

public class listaEmpleadosAdapter extends RecyclerView.Adapter<EmpleadoViewHolder>{
    private Context c;
    private ArrayList <Empleado> listaEmpleados;
    private LayoutInflater mInflater;


    public listaEmpleadosAdapter(Context c, ArrayList<Empleado> listaEmpleados) {
        this.c = c;
        this.listaEmpleados = listaEmpleados;
        mInflater = LayoutInflater.from(c);
    }

    public LayoutInflater getmInflater() {
        return mInflater;
    }

    public void setmInflater(LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }

    public Context getC() {
        return c;
    }

    public void setC(Context c) {
        this.c = c;
    }

    public ArrayList<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(ArrayList<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }


    @NonNull
    @Override
    public EmpleadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_recyclerview_empleado, parent, false);
        return new EmpleadoViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull EmpleadoViewHolder holder, int position) {
        Empleado empleadoActual = listaEmpleados.get(position);
        holder.txt_nombreEmpleado4.setText("Nombre del empleado: " + empleadoActual.getNombre());
        holder.txt_apellidosEmpleado.setText("Apellidos: " + empleadoActual.getApellido());
        holder.txt_departamenoEmpleado.setText(String.valueOf("Departamento: " + empleadoActual.getIdDepartamento()));

    }
    @Override
    public int getItemCount() {
        return listaEmpleados.size();
    }



}
