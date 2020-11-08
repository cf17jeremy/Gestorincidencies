package com.example.gestorincidencies;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link menu#} factory method to
 * create an instance of this fragment.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class menu extends Fragment {

    private final int[] BTNMENU = {R.id.afegir, R.id.llistar, R.id.eliminar};

    public menu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View menu = inflater.inflate(R.layout.fragment_menu, container, false);

        final Button btnAfegir = menu.findViewById(R.id.afegir);
        btnAfegir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager menuManager = getFragmentManager();
                FragmentTransaction menuTransaction = menuManager.beginTransaction();
                Fragment fragmentAddIncidencia = new addincidencias();
                menuTransaction.replace(R.id.fragmentID, fragmentAddIncidencia );

                menuTransaction.commit();
            }
        });

        final Button btnLlistar = menu.findViewById(R.id.llistar);
        btnLlistar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager menuManager = getFragmentManager();
                FragmentTransaction menuTransaction = menuManager.beginTransaction();
                Fragment fragmentList = new ListIncidencia();
                menuTransaction.replace(R.id.fragmentID, fragmentList );

                menuTransaction.commit();
            }
        });

        final Button btnEliminar = menu.findViewById(R.id.eliminar);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int numIncidencies = ((conector)getActivity()).arrayincidencias.size();
                Toast toast = Toast.makeText(((MainActivity)getActivity()).getApplicationContext(), String.valueOf(numIncidencies), Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        return menu;
    }
}