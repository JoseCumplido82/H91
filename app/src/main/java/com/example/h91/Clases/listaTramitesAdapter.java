package com.example.h91.Clases;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.h91.R;

import java.util.ArrayList;

public class listaTramitesAdapter extends RecyclerView.Adapter<TramitesViewHolder> {

    private Context c;
    private ArrayList<Tramites> listaTramites;
    private LayoutInflater mInflater;

    public listaTramitesAdapter(Context c, ArrayList<Tramites> listaTramites) {
        this.c = c;
        this.listaTramites = listaTramites;
        mInflater=LayoutInflater.from(c);
    }
    public LayoutInflater getmInflater() {
        return mInflater;
    }
    public void setmInflater(LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }

    @NonNull
    @Override
    public TramitesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView= mInflater.inflate(R.layout.item_recyclerview_tramites,parent, false);
        return new TramitesViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull TramitesViewHolder holder, int position) {
        Tramites tramitesActual= listaTramites.get(position);
        String nombreEstadoTramite="";
        if(tramitesActual.getIdEstado()==1){
            nombreEstadoTramite= "En proceso";
        }else if(tramitesActual.getIdEstado()==2){
            nombreEstadoTramite= "Aprobado";
        }else if(tramitesActual.getIdEstado()==3){
            nombreEstadoTramite= "Cancelado";
        }else if(tramitesActual.getIdEstado()==4){
            nombreEstadoTramite= "Denegado";
        }else if(tramitesActual.getIdEstado()==5){
            nombreEstadoTramite= "Finalizado";
        }


        holder.txt_idEstado.setText("Estado tramite: " + nombreEstadoTramite);
  //      holder.txt_fecha_solicitud.setText((CharSequence) tramitesActual.getFecha_solicitud());
    //    holder.txt_comentario.setText(tramitesActual.getComentario());
        holder.txt_asunto3.setText("Asunto: " + tramitesActual.getAsunto());
        holder.txt_nombre_documento.setText(tramitesActual.getNombre_documento());

    }

    @Override
    public int getItemCount(){ return listaTramites.size();
    }
    public Context getC() {
        return c;
    }

    public void setC(Context c) {
        this.c = c;
    }

    public ArrayList<Tramites> getListaTramites() {
        return listaTramites;
    }

    public void setListaTramites(ArrayList<Tramites> listaTramites) {
        this.listaTramites = listaTramites;
    }
}
