package com.example.h91.Clases;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.h91.MostrarDetalleSolicitud;
import com.example.h91.R;

import static com.example.h91.ActivityOtrasSolicitudes.EXTRA_OBJETO_SOLICITUD;
public class TramitesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView txt_detalleSolicitud=null;
    TextView txt_nombre_documento=null;
    TextView txt_asunto3=null;
    TextView txt_comentario=null;
    TextView txt_fecha_solicitud=null;
    TextView txt_idEstado=null;
    Button bt_gestionar=null;



    final listaTramitesAdapter lcAdapter;

    public TramitesViewHolder(@NonNull View itemView, listaTramitesAdapter tcAdapter) {
        super(itemView);
        txt_detalleSolicitud= (TextView) itemView.findViewById(R.id.txt_detalleSolicitud);
        txt_nombre_documento=(TextView)itemView.findViewById(R.id.txt_nombre_documento);
        txt_asunto3=(TextView) itemView.findViewById(R.id.txt_asunto3);
        txt_comentario=(TextView)itemView.findViewById(R.id.txt_comentario);
        txt_fecha_solicitud=(TextView)itemView.findViewById(R.id.txt_fecha_solicitud);
        txt_idEstado=(TextView)itemView.findViewById(R.id.txt_idEstado);
        bt_gestionar=(Button)itemView.findViewById(R.id.bt_gestionar);
        this.lcAdapter= tcAdapter;
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
    int mPosition= getLayoutPosition();
    Tramites tramites= this.lcAdapter.getListaTramites().get(mPosition);
    lcAdapter.notifyDataSetChanged();
    Intent intent= new Intent(lcAdapter.getC(), MostrarDetalleSolicitud.class);
    Tramites tramites1= new Tramites(tramites.getIdSolicitante(), tramites.getNombre_documento(), tramites.getAsunto(), tramites.getComentario(), tramites.getFecha_solicitud(), tramites.getIdEstado());
    intent.putExtra(EXTRA_OBJETO_SOLICITUD, tramites1);
    lcAdapter.getC().startActivity(intent);
    }
}
