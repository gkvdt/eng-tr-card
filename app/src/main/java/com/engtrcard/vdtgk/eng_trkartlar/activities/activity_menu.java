package com.engtrcard.vdtgk.eng_trkartlar.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.engtrcard.vdtgk.eng_trkartlar.R;


public class activity_menu extends AppCompatActivity {

    Button bCard,bDic,bChoose,bSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bCard = findViewById(R.id.kCard);
        bChoose = findViewById(R.id.sCard);


        bCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kapalı kartlar 5 cevaplı
                startActivity(new Intent(activity_menu.this,CardActivity.class));

            }
        });


        bChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 2 şıklı kartlar
                startActivity(new Intent(activity_menu.this, activity_select_card.class));
            }
        });
    }
}
