package com.garcesito.restauranteapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.garcesito.restauranteapp.clases.Utilidades;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class Main2Activity extends AppCompatActivity


        implements NavigationView.OnNavigationItemSelectedListener, MenuFragment.OnFragmentInteractionListener,
        MisPedidosFragment.OnFragmentInteractionListener,InfoFragment.OnFragmentInteractionListener,ContenedorFragment.OnFragmentInteractionListener, GoogleApiClient.OnConnectionFailedListener, Contenedor2Fragment.OnFragmentInteractionListener,
        BebidasFragment.OnConnectionFailedListener, PostresFragment.OnConnectionFailedListener, PlatofuerteFragment.OnConnectionFailedListener {
    GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        SharedPreferences prefs = getSharedPreferences("Logueo", Context.MODE_PRIVATE);

        int logopc = prefs.getInt("LogOpc",0);

        if(logopc == 1)
        {
            // hacer esto si entro con facebook
            Toast.makeText(getApplicationContext(), "entraste con facebook",Toast.LENGTH_SHORT).show();
            if(AccessToken.getCurrentAccessToken()== null)
            {
                goLoginActivity();

            }
        }else if(logopc == 2)
        {
            Toast.makeText(getApplicationContext(), "entraste con google",Toast.LENGTH_SHORT).show();

            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestProfile()
                    .build();

            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this,this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                    .build();

        }else if(logopc == 3)
        {
            Toast.makeText(getApplicationContext(), "entraste con registro",Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(getApplicationContext(), "entro sin requerir login",Toast.LENGTH_SHORT).show();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        if (Utilidades.validarPantalla) {
            Fragment fragment = new ContenedorFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main2,fragment).commit();
            Utilidades.validarPantalla=false;
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void goLoginActivity() {
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class );
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.mCerrar2) {
            SharedPreferences prefs = getSharedPreferences("Logueo", Context.MODE_PRIVATE);
            int logopc = prefs.getInt("LogOpc",0);

            if(logopc == 1)
            {
                //cerrar facebook
                LoginManager.getInstance().logOut();


            }
            else if(logopc == 2)
            {
                //cerrar google
                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                // ...
                            }
                        });

            }else if(logopc == 3)
            {

            }
            else
            {
                Toast.makeText(getApplicationContext(), "entro sin requerir login",Toast.LENGTH_SHORT).show();
            }


            //reestablecer la opcion de logueo
            Utilidades.validarPantalla=true;
            Utilidades.rotacion=0;
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("LogOpc",0);
            editor.commit();
            goLoginActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment miFragment = null;
        boolean fragmentSeleccionado = false;

        if (id == R.id.nav_menu) {
            miFragment = new MenuFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_menutab) {
            miFragment = new Contenedor2Fragment();
            fragmentSeleccionado=true;

        } else if (id == R.id.nav_info) {
            miFragment = new InfoFragment();
            fragmentSeleccionado=true;

        } else if (id == R.id.nav_tab) {
            miFragment = new ContenedorFragment();
            fragmentSeleccionado=true;

        }

        if(fragmentSeleccionado)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main2,miFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(getApplicationContext(), "fallo la conexion con google",Toast.LENGTH_SHORT).show();
        goLoginActivity();
    }


}
