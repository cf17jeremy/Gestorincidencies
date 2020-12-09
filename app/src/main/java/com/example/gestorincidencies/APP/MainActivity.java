package com.example.gestorincidencies.APP;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.gestorincidencies.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button login;
    EditText usuari,contrasenya;
    private CheckBox RememberMe;
    private saveprefecence savpref;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        savpref = new saveprefecence(getApplicationContext());
        if(!new saveprefecence(this).noidioma()){
            setAppLocale(savpref.getidioma());
        }
        //declaracion checkbox
        RememberMe = findViewById(R.id.rememberuser);
        RememberMe.setText(getResources().getString(R.string.remembertxt));

        if (!new saveprefecence(this).isUserLogedOut()) {
            //si estan guardados entra directamente al menu
            gotomenu();
        }

        //variables
        usuari = findViewById(R.id.user);
        contrasenya = findViewById(R.id.contrasenya);
        login = findViewById(R.id.btnlog);
        usuari.setHint(getResources().getString(R.string.user));
        contrasenya.setHint(getResources().getString(R.string.contra));
        login.setText(getResources().getString(R.string.login));


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login usu = new login();
                if ((usuari.getText().toString().trim().equals(usu.getuser())) && (contrasenya.getText().toString().trim().equals(usu.getpasswd()))) {
                    if (RememberMe.isChecked()) {
                        saveLoginDetails(usuari.getText().toString().trim(), contrasenya.getText().toString().trim());
                    }
                    SharedPreferences sp = getSharedPreferences("LoginDetails" , Context.MODE_PRIVATE);
                    savpref.getUser();
                    savpref.getPass();
                    gotomenu();
                } else {
                    Snackbar.make(v, getResources().getString(R.string.errorLogin), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    private void saveLoginDetails(String user, String password) {
        new saveprefecence(this).saveLoginDetails(user, password);
    }

    public void gotomenu(){
        Intent intent = new Intent (MainActivity.this, conector.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setAppLocale(String localeCode){
        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(new Locale(localeCode.toLowerCase()));
        resources.updateConfiguration(configuration, displayMetrics);
        configuration.locale = new Locale(localeCode.toLowerCase());
        resources.updateConfiguration(configuration, displayMetrics);
    }
}