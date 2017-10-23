package com.garcesito.restauranteapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.facebook.FacebookSdk.getApplicationContext;


public class MenuFragment extends Fragment implements View.OnClickListener,PlatofuerteFragment.OnFragmentInteractionListener,BebidasFragment.OnFragmentInteractionListener,PostresFragment.OnFragmentInteractionListener {
    private BottomNavigationView bottomNavigationView2;
    Button bPlato,bPostres,bBebidas;
    FragmentTransaction fragmentTransaction;
    View view;
    final Fragment PlatofuerteFragment = new PlatofuerteFragment();
    final Fragment BebidasFragment = new BebidasFragment();
    final Fragment PostresFragment = new PostresFragment();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, PlatofuerteFragment).commit();

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_menu, container, false);
        bottomNavigationView2 = (BottomNavigationView) view.findViewById(R.id.bottomNavigationView);
        bottomNavigationView2.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.platofuerte:

                        fragmentTransaction = getChildFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentContainer, PlatofuerteFragment).commit();
                        break;
                    case R.id.postres:

                        fragmentTransaction = getChildFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentContainer, PostresFragment).commit();
                        break;
                    case R.id.bebida:

                        fragmentTransaction = getChildFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentContainer, BebidasFragment).commit();
                        break;

                }

                return true;
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {


    }


    public interface OnFragmentInteractionListener {
    }
}
