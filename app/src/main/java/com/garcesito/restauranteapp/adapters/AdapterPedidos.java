package com.garcesito.restauranteapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.garcesito.restauranteapp.ProductoActivity;
import com.garcesito.restauranteapp.R;
import com.garcesito.restauranteapp.clases.Platos;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by pepel on 8/11/2017.
 */

public class AdapterPedidos extends RecyclerView.Adapter<AdapterPedidos.PlatosViewHolder> {

    static ArrayList<Platos> platosfuertes;
    static String Nombre;
    static String user;
    static String item;



    public AdapterPedidos(ArrayList<Platos> platosfuertes) {
        this.platosfuertes = platosfuertes;
    }

    @Override
    public PlatosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler_pedidos,null,false);
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
        Button bModificar,bQuitar;

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
           // bModificar =itemView.findViewById(R.id.bModificar);
            bQuitar =itemView.findViewById(R.id.bQuitar);

        }

        public void setOnEvento() {

            bProducto.setOnClickListener(this);
            //bModificar.setOnClickListener(this);
            bQuitar.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            switch (view.getId()){
                /*case R.id.bModificar    :

                    int position = Integer.parseInt(String.valueOf(tID.getText()));
                    Toast.makeText(context,"entro a "+tID.getText(),Toast.LENGTH_SHORT).show();
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

                   // Toast.makeText(context,"entro a "+tID.getText(),Toast.LENGTH_SHORT).show();

                break;*/
                case R.id.bQuitar:

                    final FirebaseDatabase database;
                    final DatabaseReference myRef;
                    final int position = Integer.parseInt(String.valueOf(tID.getText()));

                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference("RestauranteApp").child("Usuarios");

                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                                String correo = snapshot.child("Correo").getValue(String.class);
                                if(correo.equals("user1@correo.com"))
                                {
                                    user =snapshot.child("ID").getValue(String.class);
                                    //Toast.makeText(context,user,Toast.LENGTH_SHORT).show();
                                }
                            }
                            final DatabaseReference myRef2;
                            myRef2=database.getReference("RestauranteApp").child("Usuarios").child(user).child("Items");

                            myRef2.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    int i=0;
                                    for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                                        if (snapshot.child("Nombre").exists()) {
                                            if (i == position) {
                                                item = snapshot.getKey();
                                            }
                                            i++;

                                        }
                                    }
                                    final DatabaseReference myRef3;
                                    myRef3=database.getReference("RestauranteApp").child("Usuarios").child(user).child("Items").child(item);
                                    //Toast.makeText(context,item,Toast.LENGTH_SHORT).show();
                                    myRef3.removeValue();
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


            }
        }
    }
}
