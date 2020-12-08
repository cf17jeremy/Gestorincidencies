package com.example.gestorincidencies.APP;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gestorincidencies.R;
import com.fangxu.allangleexpandablebutton.AllAngleExpandableButton;
import com.fangxu.allangleexpandablebutton.ButtonData;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class config extends Fragment {

    public config() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View config = inflater.inflate(R.layout.fragment_config, container, false);
        AllAngleExpandableButton button = (AllAngleExpandableButton)config.findViewById(R.id.button_expandable);
        final List<ButtonData> buttonDatas = new ArrayList<>();
        int[] drawable = {R.drawable.plus, R.drawable.eliminar, R.drawable.lista, R.drawable.plus};
        for (int i = 0; i < drawable.length; i++) {
            ButtonData buttonData = ButtonData.buildIconButton((getActivity()).getApplicationContext(), drawable[i], 0);
            buttonDatas.add(buttonData);
        }
        button.setButtonDatas(buttonDatas);
        return config;
    }
}