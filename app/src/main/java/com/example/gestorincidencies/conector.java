package com.example.gestorincidencies;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class conector extends AppCompatActivity {
    protected ArrayList<incidencia> arrayincidencias;

    protected Fragment[] menuFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayincidencias = new ArrayList<incidencia>();
        Bundle bundle = new Bundle();
        bundle.putSerializable("arrayincidencias", arrayincidencias);

    }
}
