package com.garcesito.restauranteapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {
    private String correo, contrasena,repconrasena;
    EditText eCorreo,eContrasena,eRepContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        eCorreo = (EditText) findViewById(R.id.eCorreo);
        eContrasena = (EditText) findViewById(R.id.eContrasena);
        eRepContrasena = (EditText) findViewById(R.id.eRepContrasena);
    }


    public void registrar(View view) {

        correo = eCorreo.getText().toString();
        contrasena = eContrasena.getText().toString();
        repconrasena = eRepContrasena.getText().toString();
        if ((repconrasena.equals(contrasena)) && (repconrasena != null)) {
            if (correo.contains("@")) {
                SharedPreferences prefs = getSharedPreferences("Logueo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("correo", correo);
                editor.putString("contrasena", contrasena);
                editor.commit();
                Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Correo no valido", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();

        }
    }
}
