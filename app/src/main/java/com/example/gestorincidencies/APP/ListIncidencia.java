package com.example.gestorincidencies.APP;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gestorincidencies.BDD.DBDCreation;
import com.example.gestorincidencies.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListIncidencia#} factory method to
 * create an instance of this fragment.
 */
public class ListIncidencia extends Fragment {
    //Creacion instancia
    private DBDCreation DBCreation;

    public ListIncidencia() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View listIncidencia = inflater.inflate(R.layout.fragment_list_incidencia, container, false);

        RecyclerView recyclerView = listIncidencia.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(listIncidencia.getContext()));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, DBCreation.LIncidencia());

        recyclerView.setAdapter(adapter);

        return listIncidencia;
    }
}