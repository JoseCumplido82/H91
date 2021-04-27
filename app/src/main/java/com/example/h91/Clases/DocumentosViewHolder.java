package com.example.h91.Clases;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h91.MostrarDetalleDocumentosActivity;
import com.example.h91.R;

public class DocumentosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txt_asunto= null;
    public TextView txt_fecha= null;

    final listaDocumentosAdapter lcAdapter;

    public DocumentosViewHolder(@NonNull View itemView, listaDocumentosAdapter lcAdapter){
        super(itemView);
        txt_asunto=(TextView) itemView.findViewById(R.id.txt_nombret);
        txt_fecha=(TextView) itemView.findViewById(R.id.txt_asuntot);
        this.lcAdapter = lcAdapter;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int mPosition = getLayoutPosition();
        Documentos documentos=this.lcAdapter.getDocumentosList().get(mPosition);
        Log.i("documentos", "has seleccionado" + documentos.toString());
        lcAdapter.notifyDataSetChanged();
        Intent intent = new Intent(lcAdapter.getC(), MostrarDetalleDocumentosActivity.class);
        lcAdapter.getC().startActivity(intent);

    }
}
