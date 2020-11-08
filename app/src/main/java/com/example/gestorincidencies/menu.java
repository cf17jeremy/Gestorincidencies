package com.example.gestorincidencies;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.nightonke.boommenu.Util;

import java.util.ArrayList;
import java.util.List;

public class menu extends AppCompatActivity {
    private static int[] img = new int[]{
            R.drawable.plus,
            R.drawable.lista,
            R.drawable.eliminar,
    };

    public void constructor (int foto, String text, String text2){
        HamButton.Builder builder = new HamButton.Builder()
                .normalImageRes(foto)
                .normalText(text)
                .subNormalText(text2);
        BoomMenuButton bmb = (BoomMenuButton) findViewById(R.id.bmb);
        bmb.addBuilder(builder);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        BoomMenuButton bmb = (BoomMenuButton) findViewById(R.id.bmb);
        bmb.setButtonEnum(ButtonEnum.Ham);
        constructor(img[0],"Añadir","Añada una nueva incidencia");
        constructor(img[1],"Listar","Listar todas las incidencias");
        constructor(img[2],"Eliminar","Elimina una incidencia");
    }
}
