package com.garcesito.restauranteapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.garcesito.restauranteapp.adapters.Adaptadorprueba;
import com.garcesito.restauranteapp.adapters.AdapterPedidos;
import com.garcesito.restauranteapp.adapters.AdapterPlatos;
import com.garcesito.restauranteapp.clases.Platos;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static android.os.SystemClock.sleep;


public class MisPedidosFragment extends Fragment {
    private static final long SPLASH_DELAY = 2000;
    RecyclerView rv;
    ArrayList<Platos> platosfuertes;
    AdapterPedidos adapterplatos;
    FirebaseDatabase database;
    DatabaseReference myRef,myRef2,myRef3;
    String[] ing= new String[10];
    Button bOrdenar;
    TextView tTotal;
    Integer Total=0;
    String total;
    int k=0;


    static String user;

    ArrayList<String> listDatos;
    Adaptadorprueba adaptador;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_mispedidos, container, false);

        rv =v.findViewById(R.id.recycler2);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        platosfuertes = new ArrayList<>();

        bOrdenar=v.findViewById(R.id.bOrdenar);
        tTotal=v.findViewById(R.id.tTotal);




/*
       listDatos= new ArrayList<>();

        for (int i=0;i<=50;i++)
        {
            listDatos.add("Dato # "+i+"");
        }

        adaptador = new Adaptadorprueba(listDatos);
        rv.setAdapter(adaptador);*/

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("RestauranteApp").child("Usuarios");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    String correo = snapshot.child("Correo").getValue(String.class);
                    if(correo.equals("user1@correo.com"))
                    {
                        user =snapshot.child("ID").getValue(String.class);
                        //Toast.makeText(getContext(),user,Toast.LENGTH_SHORT).show();
                    }
                }

                database = FirebaseDatabase.getInstance();
                myRef2 = database.getReference("RestauranteApp").child("Usuarios").child(user).child("Items");


                myRef2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //sleep(5000);
                        platosfuertes.removeAll(platosfuertes);
                        Total=0;
                        total="0";
                        tTotal.setText(total);
                        for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                            if (snapshot.child("Nombre").exists()) {
                                String nombre = snapshot.child("Nombre").getValue(String.class);
                                //Toast.makeText(getContext(), nombre, Toast.LENGTH_SHORT).show();
                                //int precio = snapshot.child("Precio").getValue(Integer.class);
                                //String precioS= Integer.toString(precio);
                                String precioS =snapshot.child("Precio").getValue(String.class);
                                String descripcion = snapshot.child("Descripcion").getValue(String.class);
                                String uri2 = snapshot.child("Imagen").getValue(String.class);

                                String ing0 = snapshot.child("ing0").getValue(String.class);
                                String ing1 = snapshot.child("ing1").getValue(String.class);
                                String ing2 = snapshot.child("ing2").getValue(String.class);
                                String ing3 = snapshot.child("ing3").getValue(String.class);
                                String ing4 = snapshot.child("ing4").getValue(String.class);
                                String ing5 = snapshot.child("ing5").getValue(String.class);
                                String ing6 = snapshot.child("ing6").getValue(String.class);
                                String ing7 = snapshot.child("ing7").getValue(String.class);
                                String ing8 = snapshot.child("ing8").getValue(String.class);
                                String ing9 = snapshot.child("ing9").getValue(String.class);

                                int uri = R.drawable.hamburguesa;
                                Integer precio= 0;
                                try {
                                    precio = Integer.valueOf(precioS);
                                } catch (NumberFormatException e) {
                                    //System.out.println("fallo al intentar parcear el numero "+precioS);
                                    e.printStackTrace();
                                }


                                // int precio =10000;
                                Total=Total+precio;
                                total = String.valueOf(Total);
                                tTotal.setText(total);
                                // tTotal.setText(precioS);

                                platosfuertes.add(new Platos(nombre,precioS,descripcion,uri2,ing0,ing1,ing2,ing3,ing4,ing5,ing6,ing7,ing8,ing9));

                            }else
                            {
                                tTotal.setText(total);
                            }

                        }
                        adapterplatos.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        adapterplatos = new AdapterPedidos(platosfuertes);
        rv.setAdapter(adapterplatos);

        bOrdenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"ordenar",Toast.LENGTH_SHORT).show();

                database = FirebaseDatabase.getInstance();
                int n=platosfuertes.size();
                for(int i=0;i<n;i++)
                {

                    myRef3 = database.getReference("RestauranteApp").child("Pedidos").child(user).child("Pedido"+k).
                            child("Item"+i).child("Nombre");
                    myRef3.setValue(platosfuertes.get(i).getNombre());
                    myRef3 = database.getReference("RestauranteApp").child("Pedidos").child(user).child("Pedido"+k).
                            child("Item"+i).child("ing0");
                    myRef3.setValue(platosfuertes.get(i).getIng0());
                    myRef3 = database.getReference("RestauranteApp").child("Pedidos").child(user).child("Pedido"+k).
                            child("Item"+i).child("ing1");
                    myRef3.setValue(platosfuertes.get(i).getIng1());
                    myRef3 = database.getReference("RestauranteApp").child("Pedidos").child(user).child("Pedido"+k).
                            child("Item"+i).child("ing2");
                    myRef3.setValue(platosfuertes.get(i).getIng2());
                    myRef3 = database.getReference("RestauranteApp").child("Pedidos").child(user).child("Pedido"+k).
                            child("Item"+i).child("ing3");
                    myRef3.setValue(platosfuertes.get(i).getIng3());
                    myRef3 = database.getReference("RestauranteApp").child("Pedidos").child(user).child("Pedido"+k).
                            child("Item"+i).child("ing4");
                    myRef3.setValue(platosfuertes.get(i).getIng4());
                    myRef3 = database.getReference("RestauranteApp").child("Pedidos").child(user).child("Pedido"+k).
                            child("Item"+i).child("ing5");
                    myRef3.setValue(platosfuertes.get(i).getIng5());
                    myRef3 = database.getReference("RestauranteApp").child("Pedidos").child(user).child("Pedido"+k).
                            child("Item"+i).child("ing6");
                    myRef3.setValue(platosfuertes.get(i).getIng6());
                    myRef3 = database.getReference("RestauranteApp").child("Pedidos").child(user).child("Pedido"+k).
                            child("Item"+i).child("ing7");
                    myRef3.setValue(platosfuertes.get(i).getIng7());
                    myRef3 = database.getReference("RestauranteApp").child("Pedidos").child(user).child("Pedido"+k).
                            child("Item"+i).child("ing8");
                    myRef3.setValue(platosfuertes.get(i).getIng8());
                    myRef3 = database.getReference("RestauranteApp").child("Pedidos").child(user).child("Pedido"+k).
                            child("Item"+i).child("ing9");
                    myRef3.setValue(platosfuertes.get(i).getIng9());
                    myRef3 = database.getReference("RestauranteApp").child("Pedidos").child(user).child("Pedido"+k).
                            child("Item"+i).child("Precio");
                    myRef3.setValue(platosfuertes.get(i).getPrecio());



                }
                myRef3 = database.getReference("RestauranteApp").child("Pedidos").child(user).child("Pedido"+k).child("Total");
                myRef3.setValue(total);
                //myRef3 = database.getReference("RestauranteApp").child("Pedidos").child(user).child("Hora");
                //myRef3.setValue(Calendar.getInstance().getTime());


                clean();
            }
        });
        return v;
    }

    private void clean() {

        platosfuertes.clear();
        adapterplatos.notifyDataSetChanged();

        myRef3 = database.getReference("RestauranteApp").child("Usuarios").child(user).child("Items");
        myRef3.removeValue();

        myRef3 = database.getReference("RestauranteApp").child("Pedidos").child("Contador");
        myRef3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int turno = dataSnapshot.getValue(Integer.class);
                myRef3 = database.getReference("RestauranteApp").child("Pedidos").child(user).child("Pedido"+k).child("Turno");
                myRef3.setValue(turno);
                myRef3 = database.getReference("RestauranteApp").child("Pedidos").child("Contador");
                myRef3.setValue(turno+1);
                k++;

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public interface OnFragmentInteractionListener {


    }


}
