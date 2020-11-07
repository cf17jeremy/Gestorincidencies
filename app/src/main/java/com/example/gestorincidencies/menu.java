package com.example.gestorincidencies;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;

public class menu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        BoomMenuButton bmb = (BoomMenuButton) findViewById(R.id.bmb);
        bmb.setButtonEnum(ButtonEnum.SimpleCircle);
        for (int i = 0; i < bmb.getButtonPlaceEnum().buttonNumber(); i++) {
            bmb.addBuilder(new SimpleCircleButton.Builder()
                    .normalImageRes(R.drawable.plus));
        }
    }
}
