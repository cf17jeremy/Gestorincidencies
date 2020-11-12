package com.example.gestorincidencies.APP;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.gestorincidencies.BDD.DBDCreation;
import com.example.gestorincidencies.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addincidencias#} factory method to
 * create an instance of this fragment.
 */
public class addincidencias extends Fragment {
    //Creacion instancia
    private DBDCreation DBCreation;
    private SQLiteDatabase db;
    public void addincidencias() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DBCreation = new DBDCreation(getContext());
        db = DBCreation.getWritableDatabase();

        // Inflate the layout for this fragment
        final View addIncidencia = inflater.inflate(R.layout.addincidencies_layout, container, false);
        final String[] emergencia = new String[] {"Alta","Mediana","Baja"};
        final Spinner spinner = addIncidencia.findViewById(R.id.spinner1);
        final ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, emergencia);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setPrompt("Tipo de urgencia");
        spinner.setAdapter(adapter);

        final Button btnafegirIncidencia = addIncidencia.findViewById(R.id.btnafegirIncidencia);
        btnafegirIncidencia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String emergencia = spinner.getSelectedItem().toString() ;
                EditText txtIncidencia = addIncidencia.findViewById(R.id.txtincidencia);
                String txtIncidenciaForm = txtIncidencia.getText().toString();

                incidencia incidencia = new incidencia(txtIncidenciaForm,emergencia);
                DBDCreation.insertIncidencia(db,incidencia);
                Toast toast = Toast.makeText(getActivity(),"Incidencia creada correctamente",Toast.LENGTH_SHORT);
                toast.show();
                txtIncidencia.setText("");

            }
        });

        return addIncidencia;
    }
}