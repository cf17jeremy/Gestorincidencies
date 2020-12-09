package com.example.gestorincidencies.APP;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestorincidencies.BDD.DBDCreation;
import com.example.gestorincidencies.R;
import com.fangxu.allangleexpandablebutton.AllAngleExpandableButton;
import com.fangxu.allangleexpandablebutton.ButtonData;
import com.fangxu.allangleexpandablebutton.ButtonEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class config extends Fragment {
    private saveprefecence savpref;
    private DBDCreation dbCreation;
    private SQLiteDatabase db;
    private static Locale loc;
    Spinner idioma;

    public config() {
        // Required empty public constructor
    }
    public void thanos() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());

        // set title
        alertDialogBuilder.setTitle(R.string.dialog_info);

        // set dialog message
        alertDialogBuilder.setMessage(R.string.dialog_Advetencia);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton(R.string.dialog_aceptar, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dbCreation.eliminar();
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

    public void reseteo() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());

        // set title
        alertDialogBuilder.setTitle(R.string.dialog_info);

        // set dialog message
        alertDialogBuilder
                .setMessage(R.string.dialog_Advetencia2)
                .setCancelable(false)
                .setPositiveButton(R.string.dialog_aceptar,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dbCreation.eliminar();
                        savpref.reset();
                        System.exit(2);
                    }
                })
                .setNegativeButton(R.string.dialog_cancelar,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        Toast.makeText(getContext(), getString(R.string.dialog_respuesta),
                                Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }

    public void select(){
        idioma.setVisibility(View.VISIBLE);
    }
    public void changeLocale(String lang) {
        if (lang.equalsIgnoreCase(""))
            return;
        loc = new Locale(lang);//Set Selected Locale
        savelangDetails(lang);//Save the selected locale
        Locale.setDefault(loc);//set new locale as default
        Configuration config = new Configuration();//get Configuration
        config.locale = loc;//set config locale as selected locale
        getActivity().getResources().updateConfiguration(config, getActivity().getResources().getDisplayMetrics());//Update the config
    }

    private void savelangDetails(String lang) {
        new saveprefecence(getContext()).savidet(lang);
    }

    public void refresh (){
        Intent i = (getActivity().getIntent());
        startActivity(i);
        Fragment f = new config();
        FragmentManager menuManager = getFragmentManager();
        FragmentTransaction menuTransaction = menuManager.beginTransaction();
        menuTransaction.replace(R.id.Menu,f);
        menuTransaction.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View config = inflater.inflate(R.layout.fragment_config, container, false);
        //SPINNER
        idioma = config.findViewById(R.id.idioma);
        idioma.setVisibility(View.GONE);
        final String[] l = new String[]{getResources().getString(R.string.sel_id),getResources().getString(R.string.español),getResources().getString(R.string.ingles)};
        // Initializing an ArrayAdapter
        final ArrayAdapter adapter = new ArrayAdapter<String>(
                getActivity(),android.R.layout.simple_spinner_dropdown_item,l){
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
        idioma.setAdapter(adapter);
        //Spinner
        idioma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (idioma.getSelectedItem().toString() == l[1]){
                    changeLocale("es");
                    idioma.setVisibility(View.GONE);
                    refresh();
                } else if (idioma.getSelectedItem().toString() == l[2]){
                    changeLocale("en");
                    idioma.setVisibility(View.GONE);
                    refresh();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //shared prefrerences
        savpref = new saveprefecence(getContext());
        //Creation of the dbHelper
        dbCreation = new DBDCreation(getContext());
        db = dbCreation.getWritableDatabase();
        //MENU BUTTON
        final AllAngleExpandableButton button = config.findViewById(R.id.button_expandable);
        final List<ButtonData> buttonDatas = new ArrayList<>();
        final int[] drawable = {R.drawable.settings, R.drawable.eliminar, R.drawable.restore, R.drawable.translating};
        for (int i = 0; i < drawable.length; i++) {
            ButtonData buttonData = ButtonData.buildIconButton((getActivity()).getApplicationContext(), drawable[i], 1);
            buttonDatas.add(buttonData);
        }
        button.setButtonDatas(buttonDatas);
        button.setButtonEventListener(new ButtonEventListener() {
            @Override
            public void onButtonClicked(int index) {
                if (index == 1){
                    thanos();
                }else if (index == 2){
                    reseteo();
                }
                else  if (index == 3){
                    select();
                }
                //do whatever you want,the param index is counted from startAngle to endAngle,
                //the value is from 1 to buttonCount - 1(buttonCount if aebIsSelectionMode=true)
            }
            @Override
            public void onExpand() {
            }
            @Override
            public void onCollapse() {
            }
        });
        return config;
    }
}