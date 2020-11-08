package com.example.gestorincidencies;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
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

    public void constructor (String name, int foto, String text, String text2){
        HamButton.Builder builder = new HamButton.Builder()
                .normalImageRes(foto)
                .normalText(text)
                .subNormalText(text2)
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        FragmentManager menuManager = getFragmentManager();
                        FragmentTransaction menuTransaction = menuManager.beginTransaction();
                        Fragment fragmentAddIncidencia = new AddIncidencia();
                        menuTransaction.replace(R.id.fragmentID, fragmentAddIncidencia );

                        menuTransaction.commit();
                    }
                });
        BoomMenuButton bmb = (BoomMenuButton) findViewById(R.id.bmb);
        bmb.addBuilder(builder);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        BoomMenuButton bmb = (BoomMenuButton) findViewById(R.id.bmb);
        bmb.setButtonEnum(ButtonEnum.Ham);
        constructor("Añadirbtn",img[0],"Añadir","Añada una nueva incidencia");
        constructor("Listarbtn",img[1],"Listar","Listar todas las incidencias");
        constructor("Eliminarbtn",img[2],"Eliminar","Elimina una incidencia");
    }
}
