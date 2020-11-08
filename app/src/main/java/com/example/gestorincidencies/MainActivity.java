package com.example.gestorincidencies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    Button login;
    EditText usuari,contrasenya;
    protected ArrayList<Incidencia> arrayIncidencies;
    protected Fragment [] menuFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuari = findViewById(R.id.user);
        contrasenya = findViewById(R.id.contrasenya);
        login = findViewById(R.id.btnlog);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((usuari.getText().toString().trim().equals("Admin")) && (contrasenya.getText().toString().trim().equals("12345"))){
                    arrayIncidencies = new ArrayList<Incidencia>();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("arrayIncidencies", arrayIncidencies);
                }
                else{
                    Snackbar.make(v, "Usuario o contraseña incorrecto!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }
}