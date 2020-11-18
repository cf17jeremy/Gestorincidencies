package com.example.gestorincidencies.APP;


import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.gestorincidencies.BDD.DBDCreation;
import com.example.gestorincidencies.R;
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
        //Creacion de la instancia
        private DBDCreation DBCreation;
        private SQLiteDatabase db;

        public menu() {
            // Required empty public constructor
        }

        private static int[] img = new int[]{
                R.drawable.plus,
                R.drawable.lista,
                R.drawable.eliminar,
        };

        public void constructor (BoomMenuButton bmb, int foto, String text, String text2){
            DBCreation = new DBDCreation(getContext());
            db = DBCreation.getWritableDatabase();

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
                                DBCreation.close();
                                db.close();
                            } else if (index == 1) {
                                FragmentManager menuManager = getFragmentManager();
                                FragmentTransaction menuTransaction = menuManager.beginTransaction();
                                Fragment fragmentList = new ListIncidencia();
                                menuTransaction.replace(R.id.fragmentID, fragmentList);

                                menuTransaction.commit();
                            } else if (index == 2) {
                                confirmacion();
                            }
                        }
                    });
            bmb.addBuilder(builder);
        }

        public void confirmacion() {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());

            // set title
            alertDialogBuilder.setTitle(R.string.dialog_info);

            // set dialog message
            alertDialogBuilder.setMessage(R.string.dialog_Advetencia);
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setPositiveButton(R.string.dialog_aceptar, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    DBCreation.eliminar();
                    Toast toast = Toast.makeText((getActivity()).getApplicationContext(), R.string.dialog_confirmacion, Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
            alertDialogBuilder.setNegativeButton(R.string.dialog_cancelar, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Toast toast = Toast.makeText((getActivity()).getApplicationContext(), R.string.dialog_respuesta, Toast.LENGTH_SHORT);
                    toast.show();
                    dialog.cancel();
                }
            });
            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();
            // show it
            alertDialog.show();
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            View menu = inflater.inflate(R.layout.menu_layout,container,false);
            BoomMenuButton bmb = menu.findViewById(R.id.bmb);
            bmb.setButtonEnum(ButtonEnum.Ham);

            constructor(bmb,img[0], getResources().getString(R.string.Añadir),getResources().getString(R.string.defAñadir));
            constructor(bmb,img[1], getResources().getString(R.string.Listar),getResources().getString(R.string.defListar));
            constructor(bmb,img[2],getResources().getString(R.string.Eliminar),getResources().getString(R.string.defEliminar));

            return menu;
        }
}