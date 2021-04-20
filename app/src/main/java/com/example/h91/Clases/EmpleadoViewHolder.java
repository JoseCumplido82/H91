package com.example.h91.Clases;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h91.MostrarDetalleEmpleadoActivity;
import com.example.h91.R;


import static com.example.h91.ActivityAnadirEmpleado.EXTRA_OBJETO_EMPLEADO;

public class EmpleadoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txt_nombreEmpleado4=null;
    public TextView txt_apellidosEmpleado=null;
    public TextView txt_departamenoEmpleado=null;


    final listaEmpleadosAdapter lcAdapter;

    public EmpleadoViewHolder(@NonNull View itemView, listaEmpleadosAdapter mAdapter) {
        super(itemView);
        txt_nombreEmpleado4 = (TextView)  itemView.findViewById(R.id.txt_nombreEmpleado4);
        txt_apellidosEmpleado = (TextView)  itemView.findViewById(R.id.txt_apellidosEmpleado);
        txt_departamenoEmpleado = (TextView)  itemView.findViewById(R.id.txt_departamenoEmpleado);
        this.lcAdapter = mAdapter;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int mPosition = getLayoutPosition();
        Empleado empleado = this.lcAdapter.getListaEmpleados().get(mPosition);
        Log.i("empleado","has seleccionado: " + empleado.toString());
        lcAdapter.notifyDataSetChanged();
        Intent intent = new Intent(lcAdapter.getC(), MostrarDetalleEmpleadoActivity.class);
        //Empleado e1 = new Empleado(empleado.getIdDepartamento(), empleado.getNombre(), empleado.getApellido());
        Empleado e1 = new Empleado(empleado.getIdDepartamento(),empleado.getUsuario(),empleado.getPass(), empleado.getNombre(), empleado.getApellido(), empleado.getDomicilio(), empleado.getCorreo(), empleado.getTelefono(), empleado.getFecha_incorporacion(), empleado.getFecha_salida());


        //fallo aqui
        intent.putExtra(EXTRA_OBJETO_EMPLEADO, e1);
        lcAdapter.getC().startActivity(intent);
    }
}
