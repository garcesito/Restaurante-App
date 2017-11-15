package com.garcesito.restauranteapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.garcesito.restauranteapp.R;
import com.garcesito.restauranteapp.clases.Platos;

import java.util.ArrayList;

/**
 * Created by pepel on 8/11/2017.
 */

public class AdapterPlatos extends RecyclerView.Adapter<AdapterPlatos.PlatosViewHolder> {

    ArrayList<Platos> platosfuertes;

    public AdapterPlatos(ArrayList<Platos> platosfuertes) {
        this.platosfuertes = platosfuertes;
    }

    @Override
    public PlatosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler,null,false);
        //PlatosViewHolder holder = new PlatosViewHolder(v);
        return new PlatosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PlatosViewHolder holder, int position) {

        //Platos platos =platosfuertes.get(position);
        holder.tPrecio.setText(platosfuertes.get(position).getPrecio());
        holder.tNombre.setText(platosfuertes.get(position).getNombre());
        holder.tDescripcion.setText(platosfuertes.get(position).getDescripcion());
        holder.iImagen.setImageResource(platosfuertes.get(position).getImagenUri());

    }

    @Override
    public int getItemCount() {
        return platosfuertes.size();
    }

    public static class PlatosViewHolder extends RecyclerView.ViewHolder{

        TextView tNombre,tPrecio,tDescripcion;
        ImageView iImagen;
        public PlatosViewHolder(View itemView) {
            super(itemView);
            tNombre =  itemView.findViewById(R.id.tNombre);
            tPrecio =  itemView.findViewById(R.id.tPrecio);
            tDescripcion = itemView.findViewById(R.id.tDescripcion);
            iImagen = itemView.findViewById(R.id.iImagen);

        }
    }
}
