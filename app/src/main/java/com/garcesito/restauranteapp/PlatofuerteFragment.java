package com.garcesito.restauranteapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.garcesito.restauranteapp.adapters.Adaptadorprueba;
import com.garcesito.restauranteapp.adapters.AdapterPlatos;
import com.garcesito.restauranteapp.clases.Platos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class PlatofuerteFragment extends Fragment {

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
        View v=inflater.inflate(R.layout.fragment_platofuerte, container, false);

        rv =v.findViewById(R.id.recycler);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        platosfuertes = new ArrayList<>();


      /* listDatos= new ArrayList<>();

        for (int i=0;i<=50;i++)
        {
            listDatos.add("Dato # "+i+"");
        }

        adaptador = new Adaptadorprueba(listDatos);
        rv.setAdapter(adaptador);*/


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("RestauranteApp").child("Platos");



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                platosfuertes.removeAll(platosfuertes);
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    String nombre = snapshot.child("Nombre").getValue(String.class);
                    int precio = snapshot.child("Precio").getValue(Integer.class);
                    String precioS= Integer.toString(precio);
                    String descripcion =snapshot.child("Descripcion").getValue(String.class);
                    String uri2=snapshot.child("Imagen").getValue(String.class);

                    String ing0=snapshot.child("Ingredientes").child("Ing"+0).getValue(String.class);
                    String ing1=snapshot.child("Ingredientes").child("Ing"+1).getValue(String.class);
                    String ing2=snapshot.child("Ingredientes").child("Ing"+2).getValue(String.class);
                    String ing3=snapshot.child("Ingredientes").child("Ing"+3).getValue(String.class);
                    String ing4=snapshot.child("Ingredientes").child("Ing"+4).getValue(String.class);
                    String ing5=snapshot.child("Ingredientes").child("Ing"+5).getValue(String.class);
                    String ing6=snapshot.child("Ingredientes").child("Ing"+6).getValue(String.class);
                    String ing7=snapshot.child("Ingredientes").child("Ing"+7).getValue(String.class);
                    String ing8=snapshot.child("Ingredientes").child("Ing"+8).getValue(String.class);
                    String ing9=snapshot.child("Ingredientes").child("Ing"+9).getValue(String.class);

                    int uri=R.drawable.hamburguesa;
                    //0x07F08005E
                    platosfuertes.add(new Platos(nombre,precioS,descripcion,uri2,ing0,ing1,ing2,ing3,ing4,ing5,ing6,ing7,ing8,ing9));
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
