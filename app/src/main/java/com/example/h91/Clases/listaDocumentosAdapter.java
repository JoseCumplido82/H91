package com.example.h91.Clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h91.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class listaDocumentosAdapter extends RecyclerView.Adapter<DocumentosViewHolder> {

    private Context c;
    private ArrayList<Documentos> documentosList;
    private DateFormat df= new SimpleDateFormat("dd/MM/yyyy");
    private LayoutInflater mInflater;

    public listaDocumentosAdapter(Context c, ArrayList<Documentos> documentosList) {
        this.c = c;
        this.documentosList=documentosList;
        mInflater = LayoutInflater.from(c);
    }
    public LayoutInflater getmInflater() {
        return mInflater;
    }
    public void setmInflater(LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }
    @NonNull
    @Override
    public DocumentosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_recyclerview_documentos, parent, false);
        return new DocumentosViewHolder(mItemView, this);

    }

    @Override
    public void onBindViewHolder(@NonNull DocumentosViewHolder holder, int position) {
        Documentos documentoActual=documentosList.get(position);
        holder.txt_asunto.setText(" "+ documentoActual.getNombre());
        holder.txt_fecha.setText(" " + documentoActual.getNombre());

    }

    @Override
    public int getItemCount() {
        return documentosList.size();
    }
    public Context getC() {return  c;}

    public void setC(Context c) {
        this.c = c;
    }

    public ArrayList<Documentos> getDocumentosList() {
        return documentosList;
    }

    public void setDocumentosList(ArrayList<Documentos> documentosList) {
        this.documentosList = documentosList;
    }
}
