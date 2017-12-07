package com.garcesito.restauranteapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.garcesito.restauranteapp.clases.Platos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class InfoFragment extends Fragment {
    TextView tActual,tTurno;
    FirebaseDatabase database;
    DatabaseReference myRef,myRef2,myRef3;
    static String user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_info, container, false);
        tActual=v.findViewById(R.id.tActual);
        tTurno=v.findViewById(R.id.tTurno);

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
                myRef2 = database.getReference("RestauranteApp").child("Pedidos");


                myRef2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int actual=dataSnapshot.child("Actual").getValue(Integer.class);
                        String Actual = String.valueOf(actual);
                        tActual.setText(Actual);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                database = FirebaseDatabase.getInstance();
                myRef3 = database.getReference("RestauranteApp").child("Pedidos").child(user);


                myRef3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                            try {
                                int turno=snapshot.child("Turno").getValue(Integer.class);
                                String Turno = String.valueOf(turno);
                                tTurno.setText(Turno);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        }

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


        return v;
    }

    public interface OnFragmentInteractionListener {
    }
}
