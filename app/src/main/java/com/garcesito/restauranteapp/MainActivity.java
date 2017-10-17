package com.garcesito.restauranteapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {


    GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main);

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

        goBaseActivity();

    }

    private void goLoginActivity() {
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class );
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private void goBaseActivity() {
        Intent intent = new Intent(getApplicationContext(),BaseActivity.class );
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    public void logout(View view) {

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
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("LogOpc",0);
        editor.commit();
        goLoginActivity();

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(getApplicationContext(), "fallo la conexion con google",Toast.LENGTH_SHORT).show();
        goLoginActivity();
    }
}
