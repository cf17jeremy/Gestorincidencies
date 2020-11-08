package com.example.gestorincidencies;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.gestorincidencies.MainActivity;

public class addincidencia extends Fragment {

    public AddIncidencia() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View addIncidencia = inflater.inflate(R.layout.fragment_add_incidencia, container, false);

        final Button btnafegirIncidencia = addIncidencia.findViewById(R.id.btnafegirIncidencia);
        btnafegirIncidencia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText txtIncidencia = addIncidencia.findViewById(R.id.txtincidencia);
                String txtIncidenciaForm = txtIncidencia.getText().toString();

                ((MainActivity)getActivity()).arrayIncidencies.add(new Incidencia(txtIncidenciaForm, "alta"));

            }
        });

        return addIncidencia;
    }
}
