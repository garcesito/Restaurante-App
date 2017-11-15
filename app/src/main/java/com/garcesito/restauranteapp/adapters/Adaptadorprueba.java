package com.garcesito.restauranteapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.garcesito.restauranteapp.R;

import java.util.ArrayList;

/**
 * Created by pepel on 13/11/2017.
 */

public class Adaptadorprueba extends RecyclerView.Adapter<Adaptadorprueba.ViewHolderDatos>{

    ArrayList<String> listDatos;

    public Adaptadorprueba(ArrayList<String> listDatos) {
        this.listDatos = listDatos;
    }

    @Override
    public ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler,parent,false);

        return new ViewHolderDatos(v);

    }

    @Override
    public void onBindViewHolder(ViewHolderDatos holder, int position) {
        holder.asignarDatos(listDatos.get(position));
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView tNombre,tPrecio;
        public ViewHolderDatos(View itemView) {
            super(itemView);
            tNombre = itemView.findViewById(R.id.tNombre);
            tPrecio = itemView.findViewById(R.id.tPrecio);
        }

        public void asignarDatos(String s) {
            tNombre.setText(s);
            tPrecio.setText(s);
        }
    }
}
