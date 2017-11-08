package com.garcesito.restauranteapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Arrays;


public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private SignInButton signInButton;
    private GoogleApiClient googleApiClient;
    EditText ecorreo,econtrasena;
    String correoR, contrasenaR,correo,contrasena;
    private static final int RC_SIGN_IN = 9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        AppEventsLogger.activateApp(this);

        setContentView(R.layout.activity_login);

        ecorreo = (EditText) findViewById(R.id.ecorreo);
        econtrasena = (EditText) findViewById(R.id.econtrasena);


        SharedPreferences prefs = getSharedPreferences("Logueo",Context.MODE_PRIVATE);

        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton)findViewById(R.id.login_button);

        signInButton = (SignInButton) findViewById(R.id.logingoogle);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,7777);
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();



        loginButton.setReadPermissions(Arrays.asList("email"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                SharedPreferences prefs =
                        getSharedPreferences("Logueo",Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("LogOpc",1);
                editor.commit();

                goMainActivity();


            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Login Cancelado",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "Login Error",Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        if(requestCode == 7777)
        {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }else
        {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }

    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess())
        {
            SharedPreferences prefs =
                    getSharedPreferences("Logueo",Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("LogOpc",2);
            editor.commit();
            goMainActivity();
        }else
        {
            Toast.makeText(getApplicationContext(), "Login Error",Toast.LENGTH_SHORT).show();
        }
    }

    private void goMainActivity(){
        Intent intent = new Intent(getApplicationContext(),Main2Activity.class );
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(getApplicationContext(), "Error de conexion",Toast.LENGTH_SHORT).show();

    }

    public void iniciar(View view) {
        SharedPreferences prefs = getSharedPreferences("Logueo", Context.MODE_PRIVATE);

        correoR = prefs.getString("correo","efecto@dominio.com");
        contrasenaR = prefs.getString("contrasena","1234");

        correo = ecorreo.getText().toString();
        contrasena = econtrasena.getText().toString();

        if(correo.equals("")||contrasena.equals(""))
        {
            Toast.makeText(getApplicationContext(), "Ingrese datos", Toast.LENGTH_SHORT).show();
        }
        else if( (correoR== null) || (contrasenaR == null))
        {
            Toast.makeText(getApplicationContext(), "No hay registros previos", Toast.LENGTH_SHORT).show();
        }
        else if((correo.equals(correoR)) && (contrasena.equals(contrasenaR)))
        {

            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("LogOpc",3);
            editor.commit();
            goMainActivity();

        }
        else
        {
            Toast.makeText(getApplicationContext(), "Cuenta o contraseña incorrecta", Toast.LENGTH_SHORT).show();
        }

    }

    public void Registrese(View view) {
        Intent intent2 = new Intent(LoginActivity.this, RegistroActivity.class);
        startActivity(intent2);


    }
}
