package com.engtrcard.vdtgk.eng_trkartlar.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.engtrcard.vdtgk.eng_trkartlar.R;
import com.engtrcard.vdtgk.eng_trkartlar.cardUtils.InitQuest;
import com.engtrcard.vdtgk.eng_trkartlar.db.Constants;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.HashMap;

public class CardActivity extends AppCompatActivity {

    private TextView tv_eng,
            tv_tr1,
            tv_tr2,
            tv_tr3,
            tv_tr4,
            tv_tr5;
    private LinearLayout layout_tr, layout_eng;

    private Button btnSave, btnShow, btnNext;

    private InitQuest questHandler;

    private Boolean toggleButton;

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    HashMap<String, String> buffer;

    private int showAd = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        defineItems();

        init();

        initQuest();

        myAds();


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButton){
                    mShow();
                }
                initQuest();
                activeFullAds();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShow();
                activeFullAds();

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questHandler.getDatabase().IncrementTrue(
                        Integer.valueOf(buffer.get(Constants.ID)),
                        Integer.valueOf(buffer.get(Constants.TRUE)));
                questHandler.getDatabase().IncrementTrue(
                        Integer.valueOf(buffer.get(Constants.ID)),
                        Integer.valueOf(buffer.get(Constants.TRUE)));
                questHandler.getDatabase().IncrementTrue(
                        Integer.valueOf(buffer.get(Constants.ID)),
                        Integer.valueOf(buffer.get(Constants.TRUE)));
                initQuest();

                activeFullAds();
            }
        });


    }

    private void activeFullAds() {
        showAd++;
        if (showAd > 5) {
            fullAds();
        }

    }

    private void mShow() {
        if (toggleButton) {
            toggleButton = false;

            layout_tr.setVisibility(View.INVISIBLE);
            layout_eng.setVisibility(View.VISIBLE);

            btnShow.setText("Göster");


        } else {
            toggleButton = true;

            btnShow.setText("Gizle");

            layout_tr.setVisibility(View.VISIBLE);
            layout_eng.setVisibility(View.INVISIBLE);
        }

    }

    private void initQuest() {


        buffer = questHandler.getMyWord();

        tv_eng.setText(buffer.get(Constants.ENG));
        tv_tr1.setText(buffer.get(Constants.TR1));
        tv_tr2.setText(buffer.get(Constants.TR2));
        tv_tr3.setText(buffer.get(Constants.TR3));
        tv_tr4.setText(buffer.get(Constants.TR4));
        tv_tr5.setText(buffer.get(Constants.TR5));

    }

    private void defineItems() {
        layout_eng = findViewById(R.id.layoutEng);
        layout_tr = findViewById(R.id.layoutTr);

        tv_eng = findViewById(R.id.engWord);
        tv_tr1 = findViewById(R.id.trWord1);
        tv_tr2 = findViewById(R.id.trWord2);
        tv_tr3 = findViewById(R.id.trWord3);
        tv_tr4 = findViewById(R.id.trWord4);
        tv_tr5 = findViewById(R.id.trWord5);

        btnNext = findViewById(R.id.btnNext);
        btnShow = findViewById(R.id.btnShow);
        btnSave = findViewById(R.id.btnSave);

    }

    private void init() {
        //layoutların görünürlüğü sıfırlanıyor
        layout_tr.setVisibility(View.INVISIBLE);
        layout_eng.setVisibility(View.VISIBLE);

        questHandler = new InitQuest(getApplicationContext());

        toggleButton = false;
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
