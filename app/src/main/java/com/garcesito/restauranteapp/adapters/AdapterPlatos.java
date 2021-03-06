package com.garcesito.restauranteapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.garcesito.restauranteapp.ProductoActivity;
import com.garcesito.restauranteapp.R;
import com.garcesito.restauranteapp.clases.Platos;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



/**
 * Created by pepel on 8/11/2017.
 */

public class AdapterPlatos extends RecyclerView.Adapter<AdapterPlatos.PlatosViewHolder> {

    static ArrayList<Platos> platosfuertes;
    static String Nombre;

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

        Platos platos =platosfuertes.get(position);
        holder.tPrecio.setText("$ "+platosfuertes.get(position).getPrecio());
        holder.tNombre.setText(platosfuertes.get(position).getNombre()+":");
        Nombre=platos.getNombre();
        holder.tDescripcion.setText(platosfuertes.get(position).getDescripcion());
        holder.tID.setText(Integer.toString(position));
        //holder.iImagen.setImageResource(platosfuertes.get(position).getImagenUri());
        Picasso.with(holder.context).load(platosfuertes.get(position).getImagenUri()).into(holder.iImagen);
        holder.setOnEvento();
    }

    @Override
    public int getItemCount() {
        return platosfuertes.size();
    }

    public static class PlatosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tNombre,tPrecio,tDescripcion,tID;
        ImageView iImagen;
        LinearLayout bProducto;

        Context context;
        public PlatosViewHolder(View itemView) {
            super(itemView);

            context =itemView.getContext();

            tNombre =  itemView.findViewById(R.id.tNombre);
            tID =  itemView.findViewById(R.id.tID);
            tPrecio =  itemView.findViewById(R.id.tPrecio);
            tDescripcion = itemView.findViewById(R.id.tDescripcion);
            iImagen = itemView.findViewById(R.id.iImagen);
            bProducto =itemView.findViewById(R.id.bProducto);

        }

        public void setOnEvento() {

            bProducto.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.bProducto:

                    int position = Integer.parseInt(String.valueOf(tID.getText()));
                    //Toast.makeText(context,"entro a "+tID.getText(),Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(context, ProductoActivity.class);
                    intent.putExtra("ID",tID.getText());
                    intent.putExtra("URL",platosfuertes.get(position).getImagenUri());
                    intent.putExtra("NOMBRE",platosfuertes.get(position).getNombre());
                    intent.putExtra("DESCRIPCION",platosfuertes.get(position).getDescripcion());
                    intent.putExtra("ING0",platosfuertes.get(position).getIng0());
                    intent.putExtra("ING1",platosfuertes.get(position).getIng1());
                    intent.putExtra("ING2",platosfuertes.get(position).getIng2());
                    intent.putExtra("ING3",platosfuertes.get(position).getIng3());
                    intent.putExtra("ING4",platosfuertes.get(position).getIng4());
                    intent.putExtra("ING5",platosfuertes.get(position).getIng5());
                    intent.putExtra("ING6",platosfuertes.get(position).getIng6());
                    intent.putExtra("ING7",platosfuertes.get(position).getIng7());
                    intent.putExtra("ING8",platosfuertes.get(position).getIng8());
                    intent.putExtra("ING9",platosfuertes.get(position).getIng9());

                    intent.putExtra("PRECIO",platosfuertes.get(position).getPrecio());


                    context.startActivity(intent);


            }
        }
    }
}
