package com.example.gestorincidencies;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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