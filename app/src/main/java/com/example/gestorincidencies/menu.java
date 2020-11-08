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
        public menu() {
            // Required empty public constructor
        }

        private static int[] img = new int[]{
                R.drawable.plus,
                R.drawable.lista,
                R.drawable.eliminar,
        };

        public void constructor (BoomMenuButton bmb, int foto, String text, String text2){
            HamButton.Builder builder = new HamButton.Builder()
                    .normalImageRes(foto)
                    .normalText(text)
                    .subNormalText(text2)
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            if (index == 0) {
                                FragmentManager menuManager = getFragmentManager();
                                FragmentTransaction menuTransaction = menuManager.beginTransaction();
                                Fragment fragmentAddIncidencia = new addincidencias();
                                menuTransaction.replace(R.id.fragmentID, fragmentAddIncidencia);

                                menuTransaction.commit();
                            } else if (index == 1) {
                                FragmentManager menuManager = getFragmentManager();
                                FragmentTransaction menuTransaction = menuManager.beginTransaction();
                                Fragment fragmentList = new ListIncidencia();
                                menuTransaction.replace(R.id.fragmentID, fragmentList);

                                menuTransaction.commit();
                            } else if (index == 2) {
                                int numIncidencies = ((conector) getActivity()).arrayincidencias.size();
                                Toast toast = Toast.makeText(((MainActivity) getActivity()).getApplicationContext(), String.valueOf(numIncidencies), Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }
                    });
            bmb.addBuilder(builder);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            View menu = inflater.inflate(R.layout.menu_layout,container,false);
            BoomMenuButton bmb = (BoomMenuButton) menu.findViewById(R.id.bmb);
            bmb.setButtonEnum(ButtonEnum.Ham);
            constructor(bmb,img[0],"Añadir","Añada una nueva incidencia");
            constructor(bmb,img[1],"Listar","Listar todas las incidencias");
            constructor(bmb,img[2],"Eliminar","Elimina una incidencia");

            return menu;
        }
}