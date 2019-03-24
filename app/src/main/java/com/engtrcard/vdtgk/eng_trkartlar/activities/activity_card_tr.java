package com.engtrcard.vdtgk.eng_trkartlar.activities;

import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.engtrcard.vdtgk.eng_trkartlar.R;
import com.engtrcard.vdtgk.eng_trkartlar.cardUtils.InitQuest;
import com.engtrcard.vdtgk.eng_trkartlar.db.Constants;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.HashMap;
import java.util.Random;

public class activity_card_tr extends AppCompatActivity {

    private TextView tv_quest, tv_cevap;
    private Button btna, btn_next, btnb,btnf;
    private InitQuest quest;

    private boolean randButton;
    private HashMap<String, String> map;
    private String TAG = "Card";

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    int showAd = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_tr);


        tv_quest = findViewById(R.id.textView);
        //tv_cevap = findViewById(R.id.sonuc);

        btna = findViewById(R.id.btna);
        btnb = findViewById(R.id.btnb);
        //btn_next = findViewById(R.id.btnnext);


        MobileAds.initialize(this, "ca-app-pub-7450156975867757~5501908855");


        quest = new InitQuest(getApplicationContext());
        newQuest();
        init();

        myAds();


        btna.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                showAd++;

                if (randButton) {
                    btna.setBackground(getDrawable(R.drawable.btn_true));
                    // true değerini 1 arttır
                    Log.e(TAG, "onClick: true değeri" + map.get(Constants.TRUE));
                    quest.getDatabase().IncrementTrue(Integer.valueOf(map.get(Constants.ID)), Integer.valueOf(map.get(Constants.TRUE)));
                } else {
                    btna.setBackground(getDrawable(R.drawable.btn_false));
                    btnb.setBackground(getDrawable(R.drawable.btn_true));
                    //true değeri 0<x ise 1 azalt
                    Log.e(TAG, "onClick: true değeri" + map.get(Constants.TRUE));
                    quest.getDatabase().DecrementTrue(Integer.valueOf(map.get(Constants.ID)), Integer.valueOf(map.get(Constants.TRUE)));
                }
                btna.setClickable(false);
                btnb.setClickable(false);
                newQuest();
                NextDelay();

            }
        });


        btnb.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                showAd++;
                if (!randButton) {
                    btnb.setBackground(getDrawable(R.drawable.btn_true));
                    // true değerini 1 arttır
                    Log.e(TAG, "onClick: true değeri" + map.get(Constants.TRUE));
                    quest.getDatabase().IncrementTrue(Integer.valueOf(map.get(Constants.ID)), Integer.valueOf(map.get(Constants.TRUE)));

                } else {
                    btnb.setBackground(getDrawable(R.drawable.btn_false));
                    btna.setBackground(getDrawable(R.drawable.btn_true));
                    //true değeri 0<x ise 1 azalt
                    Log.e(TAG, "onClick: true değeri" + map.get(Constants.TRUE));
                    quest.getDatabase().DecrementTrue(Integer.valueOf(map.get(Constants.ID)), Integer.valueOf(map.get(Constants.TRUE)));
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void reset() {

        btna.setClickable(true);
        btnb.setClickable(true);

        btna.setBackground(getDrawable(R.drawable.menu_buton_animate));
        btnb.setBackground(getDrawable(R.drawable.menu_buton_animate));
    }

    private void writeQuest() {

        tv_quest.setText(map.get(Constants.TR1));
        if (randButton) {
            btna.setText(map.get(Constants.ENG));
            btnb.setText(map.get(Constants.FALSE_ENG));

        } else {
            //doğru cevap b
            btnb.setText(map.get(Constants.ENG));
            btna.setText(map.get(Constants.FALSE_ENG));

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
                if (showAd > 5) {
                    fullAds();
                }
            }
        }, 1200);
    }

    private void fullAds() {

        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            showAd = 0;
        }
    }

    private void myAds() {

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-7450156975867757/1317043846");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-7450156975867757/6623418831");


        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

}
