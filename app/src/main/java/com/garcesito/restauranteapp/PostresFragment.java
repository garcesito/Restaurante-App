package com.garcesito.restauranteapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.garcesito.restauranteapp.adapters.Adaptadorprueba;
import com.garcesito.restauranteapp.adapters.AdapterPlatos;
import com.garcesito.restauranteapp.clases.Platos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class PostresFragment extends Fragment {

    RecyclerView rv;
    ArrayList<Platos> platosfuertes;
    AdapterPlatos adapterplatos;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String[] ing= new String[10];

    ArrayList<String> listDatos;
    Adaptadorprueba adaptador;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_postres, container, false);
        rv =v.findViewById(R.id.recycler);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        platosfuertes = new ArrayList<>();

       /*
       listDatos= new ArrayList<>();

        for (int i=0;i<=50;i++)
        {
            listDatos.add("Dato # "+i+"");
        }

        adaptador = new Adaptadorprueba(listDatos);
        rv.setAdapter(adaptador);
        */

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("RestauranteApp").child("Postres");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                platosfuertes.removeAll(platosfuertes);
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    String nombre = snapshot.child("Nombre").getValue(String.class);
                    int precio = snapshot.child("Precio").getValue(Integer.class);
                    String precioS= Integer.toString(precio);
                    String descripcion =snapshot.child("Descripcion").getValue(String.class);
                    int uri2=snapshot.child("Imagen").getValue(Integer.class);

                    for (int i=0;i<=9;i++)
                    {
                        ing[i]=snapshot.child("Ingredientes").child("Ing"+i).getValue(String.class);
                        if(ing[i].equals("Null"))
                        {

                        }else
                        {

                        }

                    }
                    //Toast.makeText(getContext(),"Imagen: "+uri2,Toast.LENGTH_SHORT).show();

                    //0x07F08005E
                    platosfuertes.add(new Platos(nombre,precioS,ing,descripcion,uri2));
                }
                adapterplatos.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        adapterplatos = new AdapterPlatos(platosfuertes);
        rv.setAdapter(adapterplatos);




        return v;
    }

    public interface OnConnectionFailedListener {
    }

    public interface OnFragmentInteractionListener {
    }
}
