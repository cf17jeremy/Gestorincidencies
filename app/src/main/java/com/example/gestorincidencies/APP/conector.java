package com.example.gestorincidencies.APP;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.gestorincidencies.R;

import java.util.ArrayList;

public class conector extends AppCompatActivity {
    protected ArrayList<incidencia> arrayincidencias;

    protected Fragment[] menuFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_menu);

        arrayincidencias = new ArrayList<incidencia>();
        Bundle bundle = new Bundle();
        bundle.putSerializable("arrayincidencias", arrayincidencias);

    }
}
