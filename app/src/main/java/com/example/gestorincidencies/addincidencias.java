package com.example.gestorincidencies;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addincidencias#} factory method to
 * create an instance of this fragment.
 */
public class addincidencias extends Fragment {

    public void addincidencias() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View addIncidencia = inflater.inflate(R.layout.addincidencies_layout, container, false);
        String[] emergencia = new String[] {"Alta","Mediana","Baja"};
        Spinner spinner = addIncidencia.findViewById(R.id.spinner1);
        final ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, emergencia);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setPrompt("Tipo de urgencia");
        spinner.setAdapter(adapter);

        final Button btnafegirIncidencia = addIncidencia.findViewById(R.id.btnafegirIncidencia);
        btnafegirIncidencia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText txtIncidencia = addIncidencia.findViewById(R.id.txtincidencia);
                String txtIncidenciaForm = txtIncidencia.getText().toString();

                ((conector)getActivity()).arrayincidencias.add(new incidencia(txtIncidenciaForm, "alta"));

            }
        });

        return addIncidencia;
    }
}