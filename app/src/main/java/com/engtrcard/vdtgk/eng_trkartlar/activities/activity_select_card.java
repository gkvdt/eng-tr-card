package com.engtrcard.vdtgk.eng_trkartlar.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.engtrcard.vdtgk.eng_trkartlar.R;

public class activity_select_card extends AppCompatActivity {

    Button bTr,bEng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_card);

        bTr = findViewById(R.id.bTr);
        bEng = findViewById(R.id.bEng);


        bTr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_select_card.this,activity_card_tr.class));
            }
        });

        bEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_select_card.this,Card.class));
            }
        });
    }
}
