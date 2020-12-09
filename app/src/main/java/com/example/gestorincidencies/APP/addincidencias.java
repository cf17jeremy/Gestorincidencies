package com.example.gestorincidencies.APP;


import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
        final String[] emergencia = new String[] {getResources().getString(R.string.typeurg),getResources().getString(R.string.spinerpref1),getResources().getString(R.string.spinerpref2),getResources().getString(R.string.spinerpref3)};
        final Spinner spinner = addIncidencia.findViewById(R.id.spinner1);
        final ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,emergencia){
            @Override
            public boolean isEnabled(int p){
                if(p == 0){
                    //hacemos que el primer item no sea seleccionable
                    return false;
                }
                else{
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView i = (TextView) view;
                if(position == 0){
                    i.setTextColor(Color.GRAY);
                }
                else {
                    i.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final Button btnafegirIncidencia = addIncidencia.findViewById(R.id.btnafegirIncidencia);
        btnafegirIncidencia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String selurgency = spinner.getSelectedItem().toString();
                EditText txtIncidencia = addIncidencia.findViewById(R.id.txtincidencia);
                String txtIncidenciaForm = txtIncidencia.getText().toString();
                if (selurgency == emergencia[0]){
                    Toast toast = Toast.makeText(getActivity(),getResources().getString(R.string.toast_selecturg),Toast.LENGTH_SHORT);
                    toast.show();
                }else if(txtIncidenciaForm.length() == 0){
                    Toast toast = Toast.makeText(getActivity(),getResources().getString(R.string.toast_voidurg),Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    incidencia incidencia = new incidencia(txtIncidenciaForm, selurgency);
                    DBDCreation.insertIncidencia(db, incidencia);
                    Toast toast = Toast.makeText(getActivity(), getResources().getString(R.string.toast_correctadd), Toast.LENGTH_SHORT);
                    toast.show();
                    txtIncidencia.setText("");
                }
            }
        });

        return addIncidencia;
    }
}