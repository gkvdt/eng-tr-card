package com.engtrcard.vdtgk.eng_trkartlar.activities;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.engtrcard.vdtgk.eng_trkartlar.R;
import com.engtrcard.vdtgk.eng_trkartlar.cartUtils.InitQuest;
import com.engtrcard.vdtgk.eng_trkartlar.db.Constants;

import java.util.HashMap;
import java.util.Random;

public class Card extends AppCompatActivity {

    private TextView tv_quest, tv_cevap;
    private Button btna, btn_next, btnb;
    private InitQuest quest;

    private boolean randButton;
    private HashMap<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        tv_quest = findViewById(R.id.textView);
        //tv_cevap = findViewById(R.id.sonuc);

        btna = findViewById(R.id.btna);
        btnb = findViewById(R.id.btnb);
        //btn_next = findViewById(R.id.btnnext);

        quest = new InitQuest(getApplicationContext());
        newQuest();
        init();


        btna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (randButton) {
                    btna.setBackground(getDrawable(R.drawable.btn_true));
                } else {
                    btna.setBackground(getDrawable(R.drawable.btn_false));
                    btnb.setBackground(getDrawable(R.drawable.btn_true));
                }
                btna.setClickable(false);
                btnb.setClickable(false);
                newQuest();
                NextDelay();

            }
        });


        btnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!randButton) {
                    btnb.setBackground(getDrawable(R.drawable.btn_true));
                } else {
                    btnb.setBackground(getDrawable(R.drawable.btn_false));
                    btna.setBackground(getDrawable(R.drawable.btn_true));
                }
                btna.setClickable(false);
                btnb.setClickable(false);
                newQuest();
                NextDelay();
            }
        });


    }


    private void init() {
        randButton();
        writeQuest();
        reset();
    }


    private void randButton() {
        randButton = new Random().nextBoolean();
    }

    private void reset() {

        btna.setClickable(true);
        btnb.setClickable(true);

        btna.setBackground(getDrawable(R.drawable.btn_default));
        btnb.setBackground(getDrawable(R.drawable.btn_default));
    }

    private void writeQuest() {

        tv_quest.setText(map.get(Constants.ENG));
        if (randButton) {
            btna.setText(map.get(Constants.TR));
            btnb.setText(map.get(Constants.FALSE_TR));

        } else {
            //doğru cevap b
            btnb.setText(map.get(Constants.TR));
            btna.setText(map.get(Constants.FALSE_TR));

        }


    }

    private void newQuest() {
        map = quest.getNextQuest();

    }

    private void NextDelay() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                init();
            }
        }, 1200);
    }
}
